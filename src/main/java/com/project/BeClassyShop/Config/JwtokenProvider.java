package com.project.BeClassyShop.Config;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.project.BeClassyShop.Entity.CustomeUserDetail;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtokenProvider {
	private final String JWT_SECRET = "Somethinggggggg";
	private final long JWT_EXPITATION = 604800000L;

	public String generateToken(CustomeUserDetail userDetail) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + JWT_EXPITATION);
		Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());

		return Jwts.builder().setSubject(userDetail.getUsername()).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(key).compact();
	}
	
	public Long parseToken () {}

	
}
