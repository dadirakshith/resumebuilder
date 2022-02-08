package com.test.yantra.resume.builder.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EducationalDetails{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(length = 100)
	private String qualification;
	
	@Column(length = 100) 
	@Pattern(regexp = "^[a-zA-z &]*$", message = "Invalid value for specialization!! ")
	private String specialization;
	
	@Column
	@Min(value = 2010, message = "Year of passout should be minimum 2010")
	@Max(value = 2035, message = "Year of passout should be maximum 2035")
	private int passOutYear;
	
	@Column
	@Min(value = 40, message = "Percentage should be minimum 40%")
	@Max(value = 100, message = "Percentage should be maximum 100%")
	private double percentage;
	
	@Column(length = 100)
	@Pattern(regexp = "^[A-Za-z ']*[^ ]+$", message = "Invalid University Name!!")
	private String university;

}
