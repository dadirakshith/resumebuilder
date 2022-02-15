package com.test.yantra.resume.builder.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
	@NotBlank(message = "Project Name Name cannot be Empty!!")
	@Pattern(regexp = "^(?=.*[A-Za-z])?[\\dA-Za-z]+$", message = "Invalid Project Name!!")
	private String projectName;
	
	
	@Column
	@NotEmpty(message = "Frontend Technology Cannot be Empty!!")
	@NotNull(message = "Frontend Technology Cannot be Empty!!")
	private String [] frontendTechnology;
	
	@Column
	@NotEmpty(message = "Backend Technology Cannot be Empty!!")
	@NotNull(message = "Backend Technology Cannot be Empty!!")
	private String [] backendTechnology;
	
	@Column
	@NotEmpty(message = "Design Patterns Cannot be Empty!!")
	@NotNull(message = "Design Patterns Cannot be Empty!!")
	private String [] designPatterns;
	
	@Column
	@NotEmpty(message = "Database Info Cannot be Empty!!")
	@NotNull(message = "Database Info Cannot be Empty!!")
	private String [] databaseInfo;  
	
	@Column
	@NotEmpty(message = "Development Tools Cannot be Empty!!")
	@NotNull(message = "Development Tools Cannot be Empty!!")
	private String [] developmentTools;
	
	@Column
	@NotBlank(message = "Duration cannot be Empty!!")
	@Pattern(regexp = "^[1-2]?[0-9][ ][Years]{5}$", message = "Invalid Duration")
	private String duration;
	
	@Column
	@Min(value = 3,message = "Minimum Team size is 3")
	@Max(value = 25,message = "Maximum Team size is 25")
	private int teamSize;
	
	@Column(length = 1000)
	@NotBlank(message = "Project Description cannot be Empty!!")
	@Pattern(regexp = "^[^ ]([a-zA-Z0-9.,!:'\"()//-]+\\s)*[a-zA-Z0-9]+$", message = "Invalid Project Discription")
	private String projectDescription;
	
	@Column(length = 1000)
	@NotBlank(message = "Roles And Responsibalities cannot be Empty!!")
	@Pattern(regexp = "^[^ ]([a-zA-Z0-9.,!:()//-]+\\s)*[a-zA-Z0-9]+$", message = "Invalid Roles And Responsibalities!!")
	private String rolesAndResponsibalities;

}
