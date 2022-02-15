package com.test.yantra.resume.builder.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeBuilderListResponse {
	private boolean error;
	private String message;
	private List<String> allResumeTypeIds;
}
