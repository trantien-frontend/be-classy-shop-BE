package com.project.BeClassyShop.service;

import com.project.BeClassyShop.dto.JwtAuthenticationResponse;
import com.project.BeClassyShop.dto.SignupRequest;
import com.project.BeClassyShop.dto.SigninRequest;
import com.project.BeClassyShop.dto.SignupReponse;
import com.project.BeClassyShop.dto.UserDTO;

public interface AuthenticationService {
	SignupReponse signUp(SignupRequest signUpRequest);
	JwtAuthenticationResponse jwtAuthenticationResponse (SigninRequest signinRequest); 
}
