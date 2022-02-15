package com.test.yantra.resume.builder.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.test.yantra.resume.builder.dto.EducationalDetails;
import com.test.yantra.resume.builder.dto.EmployeeResumeDetails;
import com.test.yantra.resume.builder.dto.PersonalDetails;
import com.test.yantra.resume.builder.dto.ProjectDetails;
import com.test.yantra.resume.builder.dto.TechnologyDetails;
import com.test.yantra.resume.builder.dto.TechnologyItems;
import com.test.yantra.resume.builder.exception.InvalidTechnologiesException;
import com.test.yantra.resume.builder.exception.RelevantExperienceGreaterThanTotalExperienceException;
import com.test.yantra.resume.builder.exception.ResumeTypeIdNotPresentException;
import com.test.yantra.resume.builder.repository.EducationalDetailsRepository;
import com.test.yantra.resume.builder.repository.EmployeeResumeDetailsRepository;
import com.test.yantra.resume.builder.repository.PersonalDetailsRepository;
import com.test.yantra.resume.builder.repository.ProjectDetailsRepository;
import com.test.yantra.resume.builder.repository.TechnologyDetailsRepository;
import com.test.yantra.resume.builder.wrapper.ResumeBuilderWrapper;
import com.test.yantra.resume.builder.wrapper.TechnologyDetailsWrapper;

@Service
@Validated
public class ResumeBuilderServiceImpl implements ResumeBuilderService {

	@Autowired
	private EducationalDetailsRepository educationalDetailsRepository;

	@Autowired
	private PersonalDetailsRepository personalDetailsRepository;

	@Autowired
	private ProjectDetailsRepository projectDetailsRepository;

	@Autowired
	private TechnologyDetailsRepository technologyDetailsRepository;

	@Autowired
	private EmployeeResumeDetailsRepository employeeResumeDetailsRepository;

	@Override
	public ResumeBuilderWrapper saveResume(ResumeBuilderWrapper resume)
			throws RelevantExperienceGreaterThanTotalExperienceException {

		ResumeBuilderWrapper wrapper = new ResumeBuilderWrapper();

		EducationalDetails educationalDetails = resume.getEducationalDetails();
		wrapper.setEducationalDetails(educationalDetails);
		educationalDetailsRepository.save(educationalDetails);

		PersonalDetails personalDetails = resume.getPersonalDetails();

		wrapper.setPersonalDetails(personalDetails);
		personalDetailsRepository.save(personalDetails);

		String te = personalDetails.getTotalExperience();
		String re = personalDetails.getRelevantExperience();

		te = te.replace("year", "_");
		re = re.replace("year", "_");

		te = te.replace("month", "");
		re = re.replace("month", "");

		double years = Double.parseDouble(te.split("_")[0]);
		double months = Double.parseDouble(te.split("_")[1]);
		months = months / 12;
		years += months;
		double totalExp = years;

		years = Double.parseDouble(re.split("_")[0]);
		months = Double.parseDouble(re.split("_")[1]);
		months = months / 12;
		years += months;
		double releventExp = years;

		if (releventExp > totalExp) {
			throw new RelevantExperienceGreaterThanTotalExperienceException(
					"Relevant Experience is Greater Than Total Experience!!");
		}

		List<ProjectDetails> projectList = resume.getProjectDetails();
		wrapper.setProjectDetails(projectList);
		for (ProjectDetails project : projectList) {

			if (!validateTechnologies(project.getFrontendTechnology())) {
				throw new InvalidTechnologiesException("Invalid FrontEnd Technologies!!!");
			}
			if (!validateTechnologies(project.getBackendTechnology())) {
				throw new InvalidTechnologiesException("Invalid Backend Technologies!!!");
			}
			if (!validateTechnologies(project.getDesignPatterns())) {
				throw new InvalidTechnologiesException("Invalid Design Patterns!!!");
			}
			if (!validateTechnologies(project.getDatabaseInfo())) {
				throw new InvalidTechnologiesException("Invalid DataBase Info!!!");
			}
			if (!validateTechnologies(project.getDevelopmentTools())) {
				throw new InvalidTechnologiesException("Invalid Development Tools!!!");
			}
			projectDetailsRepository.save(project);
		}

		List<TechnologyDetailsWrapper> technologyList = resume.getTechnologyDetails();

		List<TechnologyDetails> techData = new ArrayList<>();
		wrapper.setTechnologyDetails(technologyList);
		for (TechnologyDetailsWrapper technology : technologyList) {
			TechnologyDetails tech = new TechnologyDetails();
			tech.setType(technology.getType());

			List<TechnologyItems> techItems = technology.getItems();

			String s = "";
			for (TechnologyItems items : techItems) {
				s = s.concat(items.getTechName() + ":");
				s = s.concat(items.getRatings() + ",");

			}
			tech.setItems(s);

			techData.add(tech);
			technologyDetailsRepository.save(tech);
		}

		EmployeeResumeDetails employeeResumeDetails = new EmployeeResumeDetails();
		employeeResumeDetails.setProfileId(personalDetails.getProfileId());

		String resumeTypeId = "Resume_";

		resumeTypeId += personalDetails.getTotalExperience().charAt(0) + "."
				+ personalDetails.getTotalExperience().charAt(5) + "yr";

		List<EmployeeResumeDetails> empByProfileId = employeeResumeDetailsRepository
				.findByProfileId(personalDetails.getProfileId());

		int count = 0;
		if (empByProfileId.isEmpty()) {
			count = 1;
		} else {
			Collections.sort(empByProfileId);
			String maxEmpResumeCounter = empByProfileId.get(empByProfileId.size() - 1).getResumeTypeId().split("_")[2];
			count += Integer.parseInt(maxEmpResumeCounter);
			count++;
		}
		resumeTypeId += "_" + count;

		employeeResumeDetails.setImage(resume.getPhoto());
		employeeResumeDetails.setResumeTypeId(resumeTypeId);
		employeeResumeDetails.setEmployeeId("");
		employeeResumeDetails.setEducationalDetails(educationalDetails);
		employeeResumeDetails.setPersonalDetails(personalDetails);
		employeeResumeDetails.setProjectdetails(projectList);
		employeeResumeDetails.setTechnologydetails(techData);
		employeeResumeDetailsRepository.save(employeeResumeDetails);

		wrapper.setProfileId(personalDetails.getProfileId());
		wrapper.setResumeTypeId(resumeTypeId);
		wrapper.setEmployeeId("");
		wrapper.setPhoto(resume.getPhoto());

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<ResumeBuilderWrapper>> violations = validator.validate(resume);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}

		return wrapper;
	}

	private boolean validateTechnologies(String[] technologyArr) {
		String regExp = "^[^ ](?=.*[A-Za-z])?[\\dA-Za-z ]+[^ ]$";
		for (String technology : technologyArr) {
			if (!technology.matches(regExp)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ResumeBuilderWrapper getResumeByProfileIdAndResumeType(String profileId, String resumeTypeId)
			throws ResumeTypeIdNotPresentException {
		ResumeBuilderWrapper fetchedWrapper = new ResumeBuilderWrapper();
		fetchedWrapper.setProfileId(profileId);
		fetchedWrapper.setResumeTypeId(resumeTypeId);
		fetchedWrapper.setEmployeeId("");

		try {
			EmployeeResumeDetails resumeDetails = employeeResumeDetailsRepository
					.findByProfileIdAndResumeTypeId(profileId, resumeTypeId);

			fetchedWrapper.setPhoto(resumeDetails.getImage());
			fetchedWrapper.setEducationalDetails(resumeDetails.getEducationalDetails());
			fetchedWrapper.setPersonalDetails(resumeDetails.getPersonalDetails());
			fetchedWrapper.setProjectDetails(resumeDetails.getProjectdetails());
			List<TechnologyDetails> technologiyDetailsList = resumeDetails.getTechnologydetails();

			List<TechnologyDetailsWrapper> technologyDetailsWrapperList = new ArrayList<>();
			for (TechnologyDetails technologyDetails : technologiyDetailsList) {
				TechnologyDetailsWrapper techDetailsWrapper = new TechnologyDetailsWrapper();

				techDetailsWrapper.setType(technologyDetails.getType());
				String itemsString = technologyDetails.getItems();
				String[] itemsArr = itemsString.split(",");

				List<TechnologyItems> technologyItemsList = new ArrayList<>();
				for (int i = 0; i < itemsArr.length; i++) {
					TechnologyItems techItem = new TechnologyItems();
					techItem.setTechName(itemsArr[i].split(":")[0]);
					techItem.setRatings(Integer.parseInt(itemsArr[i].split(":")[1]));
					technologyItemsList.add(techItem);
				}
				techDetailsWrapper.setItems(technologyItemsList);

				technologyDetailsWrapperList.add(techDetailsWrapper);
			}
			fetchedWrapper.setTechnologyDetails(technologyDetailsWrapperList);
		} catch (ConstraintViolationException e) {
			throw e;
		} catch (Exception e) {
			throw new ResumeTypeIdNotPresentException("Data is not present!!!");
		}

		return fetchedWrapper;
	}

	@Override
	public List<String> getResumesByProfileId(String profileId) {
		List<EmployeeResumeDetails> allResumesData = employeeResumeDetailsRepository.findByProfileId(profileId);

		List<String> allResumeTypeId = new ArrayList<>();

		for (EmployeeResumeDetails employeeResumeDetails : allResumesData) {
			allResumeTypeId.add(employeeResumeDetails.getResumeTypeId());
		}
		return allResumeTypeId;
	}

}
