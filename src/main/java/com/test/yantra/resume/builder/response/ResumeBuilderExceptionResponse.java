package com.test.yantra.resume.builder.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeBuilderExceptionResponse {
	private boolean error;
	private String message;
	private Object data;
}
