package com.test.yantra.resume.builder.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.yantra.resume.builder.common.ResumeBuilderConstants;
import com.test.yantra.resume.builder.exception.RelevantExperienceGreaterThanTotalExperienceException;
import com.test.yantra.resume.builder.exception.ResumeTypeIdNotPresentException;
import com.test.yantra.resume.builder.response.ResumeBuilderListResponse;
import com.test.yantra.resume.builder.response.ResumeBuilderResponse;
import com.test.yantra.resume.builder.service.ResumeBuilderService;
import com.test.yantra.resume.builder.wrapper.ResumeBuilderWrapper;

/**
 * 
 * 
 *
 */

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
public class ResumeBuilderController {

	@Autowired
	private ResumeBuilderService resumeBuilderService;

	/**
	 * 
	 * @param resume
	 * @return ResumeBuilderResponse
	 * @throws IOException
	 * @throws RelevantExperienceGreaterThanTotalExperienceException 
	 */
	@PostMapping("resume-builder-inventory/add-resume")
	public ResponseEntity<ResumeBuilderResponse> addResume(@RequestParam("resumeData") String resume,
			@RequestParam("media") MultipartFile myfile) throws IOException, RelevantExperienceGreaterThanTotalExperienceException {

		ObjectMapper mapper = new ObjectMapper();
		@Valid
		ResumeBuilderWrapper data = mapper.readValue(resume, ResumeBuilderWrapper.class);

		byte[] imgArr = myfile.getBytes();
		
		data.setPhoto(imgArr);

		ResumeBuilderWrapper resumeData = resumeBuilderService.saveResume(data);
		ResumeBuilderResponse response = new ResumeBuilderResponse();
		if (resumeData != null) {
			response.setError(false);
			response.setMessage(ResumeBuilderConstants.RESUME_ADD_SUCCESS_MESSAGE);
			response.setData(resumeData);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setError(true);
			response.setMessage(ResumeBuilderConstants.RESUME_ADD_FAIL_MESSAGE);
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@GetMapping("resume-builder-inventory/get-resume/{profileId}/{resumeTypeId}")
	public ResponseEntity<ResumeBuilderResponse> getResume(@PathVariable String profileId,
			@PathVariable String resumeTypeId) throws ResumeTypeIdNotPresentException {
		ResumeBuilderWrapper resumeData = resumeBuilderService.getResumeByProfileIdAndResumeType(profileId,
				resumeTypeId);
		if (resumeData != null) {
			return new ResponseEntity<>(
					new ResumeBuilderResponse(false, ResumeBuilderConstants.RESUME_PRESENT_MESSAGE, resumeData),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new ResumeBuilderResponse(true, ResumeBuilderConstants.RESUME_NOT_PRESENT_MESSAGE, resumeData),
					HttpStatus.OK);
		}
	}

	/**
	 * 
	 * @param profileId
	 * @return ResumeBuilderListResponse
	 * @throws ResumeTypeIdNotPresentException
	 */
	@GetMapping("resume-builder-inventory/get-resumes/{profileId}")
	public ResponseEntity<ResumeBuilderListResponse> getResumes(@PathVariable String profileId) {
		List<String> allResumeId = resumeBuilderService.getResumesByProfileId(profileId);
		if (allResumeId.isEmpty()) {
			return new ResponseEntity<>(
					new ResumeBuilderListResponse(true, ResumeBuilderConstants.RESUME_NOT_PRESENT_MESSAGE, null),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new ResumeBuilderListResponse(false, ResumeBuilderConstants.RESUME_PRESENT_MESSAGE, allResumeId),
					HttpStatus.OK);
		}
	}
}
