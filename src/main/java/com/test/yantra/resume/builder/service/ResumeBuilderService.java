package com.test.yantra.resume.builder.service;

import java.util.List;

import com.test.yantra.resume.builder.exception.RelevantExperienceGreaterThanTotalExperienceException;
import com.test.yantra.resume.builder.exception.ResumeTypeIdNotPresentException;
import com.test.yantra.resume.builder.wrapper.ResumeBuilderWrapper;

public interface ResumeBuilderService {
	
	public ResumeBuilderWrapper saveResume(ResumeBuilderWrapper resume) throws RelevantExperienceGreaterThanTotalExperienceException;

	public ResumeBuilderWrapper getResumeByProfileIdAndResumeType(String profileId, String resumeTypeId) throws ResumeTypeIdNotPresentException;

	public List<String> getResumesByProfileId(String profileId);
}
