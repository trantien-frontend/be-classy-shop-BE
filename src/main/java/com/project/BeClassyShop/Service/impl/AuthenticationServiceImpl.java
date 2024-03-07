package com.project.BeClassyShop.service.impl;

import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.BeClassyShop.dto.JwtAuthenticationResponse;
import com.project.BeClassyShop.dto.SignupRequest;
import com.project.BeClassyShop.dto.SigninRequest;
import com.project.BeClassyShop.dto.SignupReponse;
import com.project.BeClassyShop.dto.UserDTO;
import com.project.BeClassyShop.entity.CustomeUserDetail;
import com.project.BeClassyShop.entity.Role;
import com.project.BeClassyShop.entity.User;
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
	
	public User createUser (SignupRequest signupRequest) {
		User user = new User(); 
		if (userRepository.existsByEmail(signupRequest.getEmail())) {
			throw new RuntimeException("User đã tồn tại");
		}
		
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

	public SignupReponse signUp(SignupRequest signUpRequest) {
		User userResult = userRepository.save(createUser(signUpRequest));
		UserDTO userDTO = mapper.map(userResult, UserDTO.class);
		var jwt = jwtService.generateToken(new CustomeUserDetail(userResult));
		SignupReponse signupReponse = new SignupReponse(); 
		signupReponse.setUser(userDTO);
		signupReponse.setMessage("Đăng ký thành công");
		signupReponse.setToken(jwt);	
		
		return signupReponse; 
	}

	public JwtAuthenticationResponse jwtAuthenticationResponse(SigninRequest signinRequest) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinRequest.getUserName(), signinRequest.getPassword()));

		var user = userRepository.findByEmail(signinRequest.getUserName())
				.orElseThrow(() -> new IllegalArgumentException("Invalid User Name"));

		var jwt = jwtService.generateToken(new CustomeUserDetail(user));
		var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), new CustomeUserDetail(user));

		JwtAuthenticationResponse jwtResponse = new JwtAuthenticationResponse();
		jwtResponse.setToken(jwt);
		jwtResponse.setRefreshToken(refreshToken);

		return jwtResponse;
	}
}
