package com.project.BeClassyShop.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api")
public class AuthApi {
	@GetMapping("auth")
	public void login () {
		System.out.println("loggin");
	}
}
