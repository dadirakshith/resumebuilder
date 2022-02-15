package com.test.yantra.resume.builder.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyDetails{
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int techDetailsId;
	
	@Column(length = 50)
	@NotBlank(message = "Technology Details Type cannot be Empty!!")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Invalid Technology Details Type!!!")
	private String type;
	
	
	private String items;
	
}
