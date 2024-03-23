package com.project.BeClassyShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.BeClassyShop.entity.CustomeUserDetail;
import com.project.BeClassyShop.entity.User;
import com.project.BeClassyShop.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String theUserEmail) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(theUserEmail).get();
	
		if (user == null) {
			throw new UsernameNotFoundException(theUserEmail);
		}

		return new CustomeUserDetail(user);
//		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
//				mapRolesToAuthorities(user.getRoles()));
	}
//	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
//	}
}
