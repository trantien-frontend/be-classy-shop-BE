package com.project.BeClassyShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupReponse {
	private UserDTO User; 
	private String message; 
	private String token; 
}
