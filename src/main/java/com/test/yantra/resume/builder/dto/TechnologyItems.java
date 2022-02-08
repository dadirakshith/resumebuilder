package com.test.yantra.resume.builder.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyItems
{
	@NotEmpty(message = "Technology Name cannot be Empty!!!")
	@Pattern(regexp = "^[ A-Za-z0-9_@./#&+-]*$",message = "Invalid Tech Name!!!")
	private String techName;
	@Min(value = 0, message = "Ratings Should be Between 0 to 5 ")
	@Max(value = 5, message = "Ratings Should be Between 0 to 5 ")
	private int ratings;
}
