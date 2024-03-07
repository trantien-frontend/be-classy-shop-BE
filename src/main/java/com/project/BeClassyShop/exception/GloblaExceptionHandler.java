package com.project.BeClassyShop.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.BeClassyShop.dto.ApiResponse;

@ControllerAdvice
public class GloblaExceptionHandler {
	@ExceptionHandler(value = RuntimeException.class)
	ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException ex) {
		ApiResponse apiResponse = new ApiResponse<>();
		apiResponse.setId(1004);
		apiResponse.setMessage(ex.getMessage());
		return ResponseEntity.badRequest().body(apiResponse);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	ResponseEntity<Map<String, String>> handleRuntimeException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseEntity.badRequest().body(errors);
	}
}
