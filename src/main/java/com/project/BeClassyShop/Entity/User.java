package com.project.BeClassyShop.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.generator.EventType;

//import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "phone")
	private String phone; 
	@Column(name = "created_at")
	@CurrentTimestamp(event = EventType.INSERT)
	private Date createdAt; 
	@CurrentTimestamp(event = {EventType.INSERT, EventType.UPDATE})
	@Column(name = "updated_at")
	private Date updatedAt; 
	@Column(name = "active")
	private int active;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;

	public void addRole(Role role) {
		if (roles == null) {
			roles = new ArrayList<Role>();
		}
		roles.add(role);
	}
}
