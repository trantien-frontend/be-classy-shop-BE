package com.project.BeClassyShop.service;

import com.project.BeClassyShop.dto.ApiResponse;
import com.project.BeClassyShop.dto.SigninRequest;
import com.project.BeClassyShop.dto.SignupRequest;
import com.project.BeClassyShop.dto.UserDTO;

public interface AuthenticationService {
	ApiResponse<UserDTO> signUp(SignupRequest signUpRequest);

	ApiResponse<Object> jwtAuthenticationResponse(SigninRequest signinRequest);
}
