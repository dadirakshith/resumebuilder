package com.test.yantra.resume.builder.wrapper;

import java.util.List;

import javax.validation.Valid;

import com.test.yantra.resume.builder.dto.EducationalDetails;
import com.test.yantra.resume.builder.dto.PersonalDetails;
import com.test.yantra.resume.builder.dto.ProjectDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeBuilderWrapper {
	private String profileId;
	private String employeeId;
	private String resumeTypeId;
	private byte[] photo;

	private EducationalDetails educationalDetails;
	private PersonalDetails personalDetails;
	private List<@Valid TechnologyDetailsWrapper> technologyDetails;
	private List<ProjectDetails> projectDetails;
}
