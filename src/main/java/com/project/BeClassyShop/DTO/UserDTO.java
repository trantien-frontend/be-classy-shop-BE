package com.project.BeClassyShop.dto;

import java.sql.Date;
import java.util.Collection;

import com.project.BeClassyShop.entity.Role;

import lombok.Data;

@Data
public class UserDTO {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private Date createdAt;
	private Date updatedAt;
	private Collection<Role> roles;
}
