package com.test.yantra.resume.builder.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.test.yantra.resume.builder.wrapper.TechnologyDetailsWrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor 
public class EmployeeResumeDetails implements Comparable<EmployeeResumeDetails>{
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int resumeId;
	
	@Column(length = 100)
	@NotEmpty(message = "Profile ID is a mandatory Field!!")
	private String profileId;
	
	@Column(length = 100)
	private String resumeTypeId;
	
	@Column(length = 100)
	private String employeeId;
	
	@Lob
	private byte[] image;
	
	@OneToOne(cascade = CascadeType.ALL)
	private PersonalDetails personalDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	private EducationalDetails educationalDetails;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "resume_id")
	private List<TechnologyDetails> technologydetails;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "resume_id")
	private List<ProjectDetails> projectdetails;


	@Override
	public int compareTo(EmployeeResumeDetails e) 
	{
		return this.getProfileId().compareTo(e.getProfileId());
	}
}
