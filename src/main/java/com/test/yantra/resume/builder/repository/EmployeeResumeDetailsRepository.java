package com.test.yantra.resume.builder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.yantra.resume.builder.dto.EmployeeResumeDetails;

@Repository
public interface EmployeeResumeDetailsRepository extends JpaRepository<EmployeeResumeDetails, Integer> {

	@Query(value = "select max(resume_type_id) from resume_builder.employee_resume_details", nativeQuery = true)
	public String getMaxEmployeeResumeType();

	public List<EmployeeResumeDetails> findByProfileId(String profileId);

	public EmployeeResumeDetails findByProfileIdAndResumeTypeId(String profileId, String resumeTypeId);

}
