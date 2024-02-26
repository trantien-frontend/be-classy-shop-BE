package com.project.BeClassyShop.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class AppConfigure {
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

//	@Bean
//	public InMemoryUserDetailsManager userDetailsManager() {
//
//		UserDetails john = User.builder().username("john").password("{noop}test123").roles("EMPLOYEE").build();
//
//		UserDetails mary = User.builder().username("mary").password("{noop}test123").roles("EMPLOYEE", "MANAGER")
//				.build();
//
//		UserDetails susan = User.builder().username("susan").password("{noop}test123")
//				.roles("EMPLOYEE", "MANAGER", "ADMIN").build();
//
//		return new InMemoryUserDetailsManager(john, mary, susan);
//	}

//	@Bean
//	public UserDetailsManager userDetailsManager(DataSource dataSource) {
//		JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource); 
//		UserDetails us;
//		
//		theUserDetailsManager.setUsersByUsernameQuery("select us.username, us.password, us.enabled from users as us where us.username = ?");
//		theUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT username, autho	rity FROM authorities WHERE username = ?");
//	
//		return theUserDetailsManager; 
//	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public AuthenticationProvider authenticationProvider (UserService userService) {
//		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//		auth.setUserDetailsService(userService);
//		auth.setPasswordEncoder(passwordEncoder());
//		return auth; 
//	}

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//		http.authorizeHttpRequests(configure -> configure
//				.requestMatchers(HttpMethod.GET, "/api/banners").permitAll()
//				);
//
//		// use HTTP Basic authentications
//		http.httpBasic(Customizer.withDefaults());
//
//		// disable csrf
//		http.csrf(csrf -> csrf.disable());
//		http.cors(cors -> cors.disable()); 
//
//		return http.build();
//	}

	/*
	 * requestMatchers(HttpMethod.GET, "/api/products")
	 * .permitAll().requestMatchers(HttpMethod.GET,
	 * "/api/products/**").hasRole("EMPLOYEE") .requestMatchers(HttpMethod.POST,
	 * "/api/products").hasRole("MANAGER") .requestMatchers(HttpMethod.PUT,
	 * "/api/products").hasRole("MANAGER") .requestMatchers(HttpMethod.DELETE,
	 * "/api/products/**").hasRole("ADMIN")
	 */
}
