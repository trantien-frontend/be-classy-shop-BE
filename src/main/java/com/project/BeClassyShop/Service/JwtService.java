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


	private final String JWT_SECRET = "/X2OTSXepNTZ8+ix4Wmb7VMo9FnGzcMDoGWuNyO4BciaNfCM1om80JimyTMlo8w1"; 

	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
				.setSubject(userDetails.getUsername()) // Set Subject = userName
				.setIssuedAt(new Date(System.currentTimeMillis())) // token bắt đầu có hiệu lực
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // thời gian token hết hạn
				.signWith(getSignedKey(), SignatureAlgorithm.HS256).compact(); // ký token với JWT_SECRET và HS2
	}

//	public String generateRefreshToken(Map<String, Object> extractClaims, UserDetails userDetails) {
//		return Jwts.builder().setClaims(extractClaims).setSubject(userDetails.getUsername())
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + 604800000))
//				.signWith(getSignedKey(), SignatureAlgorithm.HS256).compact();
//	}

	private Key getSignedKey() {
		byte[] key = Decoders.BASE64.decode(JWT_SECRET);
		return Keys.hmacShaKeyFor(key);
	}

	public String extractUserName(String token) {
		var result = extractClaims(token, Claims::getSubject); //Claims trích ra token đã ký
		System.out.println("extraClasim: " + result);
		return result; 
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
