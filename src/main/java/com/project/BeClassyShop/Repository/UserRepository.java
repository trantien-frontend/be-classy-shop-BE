package com.project.BeClassyShop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BeClassyShop.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByUserName(String userName);
}
