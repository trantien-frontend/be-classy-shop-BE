package com.project.BeClassyShop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SigninRequest {
	private String email;
	private String password;
}
