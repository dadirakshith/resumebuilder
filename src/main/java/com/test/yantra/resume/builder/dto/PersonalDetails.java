package com.test.yantra.resume.builder.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@NotBlank(message = "First Name cannot be Empty!!")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Invalid First Name!!!")
	@Size(min = 3,max = 25,message = "First Name size should be Between 3 and 25")
	private String firstName;
	
	@Column
	@NotBlank(message = "Last Name cannot be Empty!!")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Invalid Last Name!!!")
	@Size(min = 1,max = 25,message = "Last Name size should be Between 1 and 25")
	private String lastName;
	
	@Column
	@NotBlank(message = "Primary Skill cannot be Empty!!")
	@Pattern(regexp = "^[a-zA-Z+#.]*$", message = "Invalid Primary Skill!!!")
	private String primarySkill;
	
	@Column(length = 1000)
	private String[] summary;
	
	@Column
	@Pattern(regexp = "^[0-2]?[0-9]{1}[year]{4}[0-9]?[0-1]?[month]{5}$" , message = "Invalid Total Experience")
	private String totalExperience;
	
	@Column
	@Pattern(regexp = "^[0-2]?[0-9]{1}[year]{4}[0-9]?[0-1]?[month]{5}$", message = "Invalid Relevant Experience")
	private String relevantExperience;
	
	@Column(length = 15)
	@NotBlank(message = "Profile Id cannot be Empty!!")
	@Pattern(regexp = "^[PRO]{3}[0-9]{10}$", message = "Invalid Profile Id!!!")
	private String profileId;
	 
}
