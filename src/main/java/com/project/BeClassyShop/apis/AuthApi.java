package com.project.BeClassyShop.apis;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.BeClassyShop.dto.ApiResponse;
import com.project.BeClassyShop.dto.SigninRequest;
import com.project.BeClassyShop.dto.SignupRequest;
import com.project.BeClassyShop.dto.UserDTO;
import com.project.BeClassyShop.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/auth")
public class AuthApi {
	private final AuthenticationService authenticationService;

	@PostMapping(path = "/signup")
	public ApiResponse<UserDTO> signUp(@Valid @RequestBody SignupRequest signUpRequest) {
		return authenticationService.signUp(signUpRequest);
	}

	@PostMapping(path = "/signin")
	public ApiResponse<Object> signIn(@RequestBody SigninRequest signinRequest) {
		System.out.println("request");
		return authenticationService.jwtAuthenticationResponse(signinRequest);
	}
}
