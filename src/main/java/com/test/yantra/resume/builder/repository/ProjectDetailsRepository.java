package com.test.yantra.resume.builder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.yantra.resume.builder.dto.ProjectDetails;
@Repository
public interface ProjectDetailsRepository extends JpaRepository<ProjectDetails, Integer>{

}
