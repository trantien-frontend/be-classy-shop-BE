package com.project.BeClassyShop.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.BeClassyShop.dto.ApiResponse;
import com.project.BeClassyShop.dto.SigninRequest;
import com.project.BeClassyShop.dto.SignupRequest;
import com.project.BeClassyShop.dto.UserDTO;
import com.project.BeClassyShop.entity.CustomeUserDetail;
import com.project.BeClassyShop.entity.Role;
import com.project.BeClassyShop.entity.User;
import com.project.BeClassyShop.exception.AppException;
import com.project.BeClassyShop.exception.ErrorCode;
import com.project.BeClassyShop.repository.RoleReponsitory;
import com.project.BeClassyShop.repository.UserRepository;
import com.project.BeClassyShop.service.AuthenticationService;
import com.project.BeClassyShop.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final RoleReponsitory roleReponsitory;

	private final AuthenticationManager authenticationManager;

	private final JwtService jwtService;

	private final ModelMapper mapper;

	public User createUser(SignupRequest signupRequest) {
		if (userRepository.existsByEmail(signupRequest.getEmail())) {
			throw new AppException(ErrorCode.USER_EXISTED);
		}

		User user = new User();
		user.setFirstName(signupRequest.getFirstName());
		user.setLastName(signupRequest.getLastName());
		user.setEmail(signupRequest.getEmail());
		user.setPhone(signupRequest.getPhone());
		user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		user.setActive(1);
		Role role = roleReponsitory.findByRoleName("ROLE_USER");
		user.addRole(role);
		return user;
	}

	public ApiResponse<UserDTO> signUp(SignupRequest signUpRequest) {
		User userResult = userRepository.save(createUser(signUpRequest));
		UserDTO userDTO = mapper.map(userResult, UserDTO.class);
		ApiResponse<UserDTO> result = new ApiResponse<>();
		result.setStatusCode(200);
		result.setBody(userDTO);
		return result;
	}

	public ApiResponse<Object> jwtAuthenticationResponse(SigninRequest signinRequest) {
		Optional<User> user = userRepository.findByEmail(signinRequest.getEmail());
		
		if (user.isEmpty()) {
			throw new AppException(ErrorCode.INVALID_EMAIL);
		}

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
		} catch (BadCredentialsException exception) {
			throw new AppException(ErrorCode.INVALID_PASSWORD);
		}
		
	

		UserDTO userDTO = mapper.map(user, UserDTO.class);
		var jwt = jwtService.generateToken(new CustomeUserDetail(user.get()));
//		var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), new CustomeUserDetail(user.get()));

		ApiResponse<Object> apiResponse = new ApiResponse<>();
		Map<String, Object> map = new HashMap<>();

		map.put("token", jwt);
		map.put("user", userDTO);

		apiResponse.setBody(map);
		apiResponse.setStatusCode(200);
//		jwtResponse.setRefreshToken(refreshToken);

		return apiResponse;
	}
}
