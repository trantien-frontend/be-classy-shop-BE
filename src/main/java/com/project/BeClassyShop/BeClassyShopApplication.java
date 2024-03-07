package com.project.BeClassyShop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.project.BeClassyShop.service.RoleService;

@SpringBootApplication
public class BeClassyShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeClassyShopApplication.class, args);
		System.out.println("Beclassy-Shop-Build-Success");
	}

	@Bean
	public CommandLineRunner commandLineRunner(RoleService roleService) {
		return runner -> {

		};
	}
}
