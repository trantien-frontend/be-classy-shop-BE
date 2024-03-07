package com.project.BeClassyShop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BeClassyShop.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByEmail(String theEmail);
	boolean existsByEmail(String theEmail); //Kiểm tra email có tồn tại chư
}
