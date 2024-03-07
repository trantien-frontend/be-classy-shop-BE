package com.project.BeClassyShop.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.BeClassyShop.dto.JwtAuthenticationResponse;
import com.project.BeClassyShop.dto.SigninRequest;
import com.project.BeClassyShop.dto.SignupRequest;
import com.project.BeClassyShop.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthApi {
	private final AuthenticationService authenticationService;

	@PostMapping(path = "/signup")
	public ResponseEntity<?> signUp(@Valid @RequestBody SignupRequest signUpRequest) {
		return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
	}

	@PostMapping(path = "/signin")
	public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SigninRequest signinRequest) {
		return ResponseEntity.ok(authenticationService.jwtAuthenticationResponse(signinRequest));
	}
}
