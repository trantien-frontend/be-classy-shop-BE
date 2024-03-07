package com.project.BeClassyShop.service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final String JWT_SECRET = "CxGUh8cRQ4yfOukWr3UVFDY/z9EVDBzOp1FHmZlTtoY=";

	public String generateToken(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignedKey(), SignatureAlgorithm.HS256).compact();
	}

	public String generateRefreshToken(Map<String, Object> extractClaims, UserDetails userDetails) {
		return Jwts.builder().setClaims(extractClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 604800000))
				.signWith(getSignedKey(), SignatureAlgorithm.HS256).compact();
	}

	private Key getSignedKey() {
		byte[] key = Decoders.BASE64.decode(JWT_SECRET);
		return Keys.hmacShaKeyFor(key);
	}

	public String extractUserName(String token) {
		return extractClaims(token, Claims::getSubject);
	}

	private <T> T extractClaims(String token, Function<Claims, T> claimsResolvers) {
		final Claims claims = extractAllClaims(token); // Claims
		return claimsResolvers.apply(claims); // apply claims
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignedKey()).build().parseClaimsJws(token).getBody();
	}

	public boolean isValidateToken(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpried(token));
	}

	private boolean isTokenExpried(String token) {
		return extractClaims(token, Claims::getExpiration).before(new Date());
	}
}
