package com.project.BeClassyShop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.BeClassyShop.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AppConfigure {
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final UserService userService;
	
	private final String[] PUBLIC_ENDPOINTS = { "/api/v1/banners", "/api/v1/products/**", "/api/v1/categories/**",
			"/api/v1/stores/**", "/api/v1/auth/signup", "/api/v1/auth/signin" };

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.exceptionHandling(handling -> handling.authenticationEntryPoint((req, res, ex) -> {
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
		}));
		// use HTTP Basic authentications
		http.httpBasic(Customizer.withDefaults());
		http.csrf(AbstractHttpConfigurer::disable); 
		http.cors(AbstractHttpConfigurer::disable);

		http.authorizeHttpRequests(configure -> configure
				.requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS).permitAll()
				.anyRequest().authenticated());

		http.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthenticationFilter,
				UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}

//@Bean
//public InMemoryUserDetailsManager userDetailsManager() {
//
//	UserDetails john = User.builder().username("john").password("{noop}test123").roles("EMPLOYEE").build();
//
//	UserDetails mary = User.builder().username("mary").password("{noop}test123").roles("EMPLOYEE", "MANAGER")
//			.build();
//
//	UserDetails susan = User.builder().username("susan").password("{noop}test123")
//			.roles("EMPLOYEE", "MANAGER", "ADMIN").build();
//
//	return new InMemoryUserDetailsManager(john, mary, susan);
//}

//@Bean
//public UserDetailsManager userDetailsManager(DataSource dataSource) {
//	JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource); 
//	UserDetails us;
//	
//	theUserDetailsManager.setUsersByUsernameQuery("select us.username, us.password, us.enabled from users as us where us.username = ?");
//	theUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT username, autho	rity FROM authorities WHERE username = ?");
//
//	return theUserDetailsManager; 
//}
