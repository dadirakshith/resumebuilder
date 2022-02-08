package com.test.yantra.resume.builder.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor 
@NoArgsConstructor
public class PersonalDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String primarySkill;
	
	@Column(length = 1000)
	private String[] summary;
	
	@Column
	@Pattern(regexp = "^[0-2]?[0-9]{1}[\"year\"]{4}[0-9]?[0-1]?[\"month\"]{5}$" , message = "Invalid Total Experience")
	private String totalExperience;
	
	@Column
	@Pattern(regexp = "^[0-2]?[0-9]{1}[\"year\"]{4}[0-9]?[0-1]?[\"month\"]{5}$", message = "Invalid Relevant Experience")
	private String relevantExperience;
	
	@Column(length = 15)
	private String profileId;
	 
}
