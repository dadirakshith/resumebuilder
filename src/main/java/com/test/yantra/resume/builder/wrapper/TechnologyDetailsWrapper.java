package com.test.yantra.resume.builder.wrapper;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.test.yantra.resume.builder.dto.TechnologyItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyDetailsWrapper {

	@NotEmpty(message = "Technology Type cannot be Empty!!!")
	@Pattern(regexp = "^[^ ][a-zA-Z ]+[^ ]$", message = "Invalid Technology Details TYpe")
	private String type;
	private List<@Valid TechnologyItems> items;
}
