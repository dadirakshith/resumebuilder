package com.test.yantra.resume.builder.handler;

import java.util.Optional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.test.yantra.resume.builder.exception.InvalidTechnologiesException;
import com.test.yantra.resume.builder.exception.RelevantExperienceGreaterThanTotalExperienceException;
import com.test.yantra.resume.builder.exception.ResumeBuilderException;
import com.test.yantra.resume.builder.exception.ResumeTypeIdNotPresentException;
import com.test.yantra.resume.builder.response.ResumeBuilderExceptionResponse;

@RestControllerAdvice
public class ResumeBuilderExceptionHandler {

	@ExceptionHandler(ResumeBuilderException.class)
	public ResponseEntity<ResumeBuilderExceptionResponse> resumeBuilderExceptionHandler(
			ResumeBuilderException exception) {
		ResumeBuilderExceptionResponse response = new ResumeBuilderExceptionResponse();
		response.setError(true);
		response.setMessage(exception.getMessage());
		response.setData(null);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResumeTypeIdNotPresentException.class)
	public ResponseEntity<ResumeBuilderExceptionResponse> resumeTypeIdNotPresentExceptionHandler(
			ResumeTypeIdNotPresentException e) {
		return new ResponseEntity<>(new ResumeBuilderExceptionResponse(true, e.getMessage(), null),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResumeBuilderExceptionResponse> constraintViolationExceptionHandler(
			ConstraintViolationException e) {
		Optional<ConstraintViolation<?>> s = e.getConstraintViolations().stream().findFirst();
		String msg = "";
		if (s.isPresent()) {
			msg = s.get().getMessage();
		}

		return new ResponseEntity<>(new ResumeBuilderExceptionResponse(true, msg, null), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RelevantExperienceGreaterThanTotalExperienceException.class)
	public ResponseEntity<ResumeBuilderExceptionResponse> relevantExperienceGreaterThanTotalExperienceExceptionHandler(
			RelevantExperienceGreaterThanTotalExperienceException e) {
		return new ResponseEntity<>(new ResumeBuilderExceptionResponse(true, e.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidTechnologiesException.class)
	public ResponseEntity<ResumeBuilderExceptionResponse> invalidTechnologiesExceptionHandler(
			InvalidTechnologiesException e) {
		return new ResponseEntity<>(new ResumeBuilderExceptionResponse(true, e.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}
}
