package com.project.BeClassyShop.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private ErrorCode errorCode;

	public AppException(ErrorCode theErrorCode) {
		super(theErrorCode.getMessage());
		this.errorCode = theErrorCode;
	}
}
