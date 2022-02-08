package com.test.yantra.resume.builder.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="project_details")
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(length = 150)
	private String projectName;
	
	
	@Column
	private String [] frontendTechnology;
	
	@Column
	private String [] backendTechnology;
	
	@Column
	private String [] designPatterns;
	
	@Column
	private String [] databaseInfo;  
	
	@Column
	private String [] developmentTools;
	
	@Column
	@Pattern(regexp = "^[1-2]?[0-9][ ][\"Years\"]{5}$", message = "Invalid Duration")
	private String duration;
	
	@Column
	@Min(value = 3,message = "Minimum Team size is 3")
	@Max(value = 25,message = "Maximum Team size is 25")
	private int teamSize;
	
	@Column
	@Pattern(regexp = "^[a-zA-Z0-9~@$^*()\\,.?: -]*$", message = "Invalid Project Discription")
	private String projectDescription;
	
	@Column
	@Pattern(regexp = "^[a-zA-Z0-9~@$^*()\\,.?: -]*$", message = "Invalid Roles And Responsibalities!!")
	private String rolesAndResponsibalities;

}
