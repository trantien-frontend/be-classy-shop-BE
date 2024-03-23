package com.project.BeClassyShop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	USER_EXISTED(1001, "User existed", 422),
	INVALID_EMAIL(1002, "Invalid email", 404),
	INVALID_PASSWORD(1003,"Invalid password",400); 

	private int code;
	private String message;
	private int statusCode; 
}
