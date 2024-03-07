package com.project.BeClassyShop.service;

import org.springframework.stereotype.Service;

import com.project.BeClassyShop.entity.Role;
import com.project.BeClassyShop.repository.RoleReponsitory;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class RoleService {
	private final RoleReponsitory roleReponsitory; 
	
	public Role getRoleByName (String roleName) {
		return this.roleReponsitory.findByRoleName(roleName); 
	} 
}
