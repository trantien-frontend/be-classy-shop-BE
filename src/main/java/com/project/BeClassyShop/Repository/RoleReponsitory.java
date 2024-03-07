package com.project.BeClassyShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BeClassyShop.entity.Role;

public interface RoleReponsitory extends JpaRepository<Role, Integer> {
	public Role findByRoleName(String theRoleName);
}
