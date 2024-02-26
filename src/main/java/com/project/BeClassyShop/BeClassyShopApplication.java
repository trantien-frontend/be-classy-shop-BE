package com.project.BeClassyShop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.project.BeClassyShop.Entity.Role;
import com.project.BeClassyShop.Entity.User;
import com.project.BeClassyShop.Service.UserService;

@SpringBootApplication
public class BeClassyShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeClassyShopApplication.class, args);
		System.out.println("Beclassy-Shop-Build-Success");
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(UserService userService) {
		return runner -> {
//				User user = userService.getUserByName("mary");
//				System.out.println(user.getRoles());
//				
//				for (Role role: user.getRoles()) {
//					System.out.println(role.getRoleName());
//				}
		};
	}
}
