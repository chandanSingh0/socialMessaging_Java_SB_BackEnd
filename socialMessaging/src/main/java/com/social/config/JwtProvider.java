package com.social.config;

import java.util.Date;


import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
	
	public static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
//	it will generate our token in string format
	public static String generateToken(Authentication auth) {
		String jwt = Jwts.builder()
				.setIssuer("SocialMsg")
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+86400000))
				.claim("email", auth.getName())
				.signWith(key)
				.compact();
		return jwt;
	}
	
	public static String getEmailfromJwtToken(String jwt) {
		jwt = jwt.substring(7);
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		String email = String.valueOf(claims.get("email"));
		return email;
	}
	
}
