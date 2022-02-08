package com.test.yantra.resume.builder.response;

import com.test.yantra.resume.builder.wrapper.ResumeBuilderWrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeBuilderResponse {
	private boolean error;
	private String message;
	private ResumeBuilderWrapper data;
}
