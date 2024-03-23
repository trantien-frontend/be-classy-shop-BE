package com.project.BeClassyShop.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.BeClassyShop.dto.ApiResponse;

@ControllerAdvice
public class GloblaExceptionHandler {
	@ExceptionHandler(value = AppException.class)
	ResponseEntity<ApiResponse<Object>> handleAppException(AppException exception) {
		ErrorCode errorCode = exception.getErrorCode();
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		Map<String, Object> errorsDetails = new HashMap<>();
		errorsDetails.put("code", errorCode.getCode());
		errorsDetails.put("message", errorCode.getMessage());
		apiResponse.setStatusCode(errorCode.getStatusCode());
		apiResponse.setBody(errorsDetails);

		if (errorCode.getCode() == 1001) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(apiResponse);
		}

		if (errorCode.getCode() == 1002) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
		}

		if (errorCode.getCode() == 1003) {
			return ResponseEntity.badRequest().body(apiResponse);
		}

		return null;
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
