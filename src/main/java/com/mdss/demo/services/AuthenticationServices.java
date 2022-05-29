package com.mdss.demo.services;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static java.util.Collections.emptyList;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationServices {
	
	public static final long TIME_DURATION = 900_000_000;
	public static final String SIGNINGKEY = "Secret";
	public static final String PREFIX = "Bearer";
	
	public static void addToken(HttpServletResponse res, String email) {
		String jwtToken = Jwts.builder().setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + TIME_DURATION))
				.signWith(SignatureAlgorithm.HS512, SIGNINGKEY)
				.compact();
		res.addHeader("Authorization",  PREFIX + jwtToken);
		res.addHeader("Access-Control-Expose-Headers", "Authoriation");
	}
	
	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token != null) {
			String email = Jwts.parser()
					.setSigningKey(SIGNINGKEY)
					.parseClaimsJws(token.replace(PREFIX,""))
					.getBody()
					.getSubject();
			if(email != null) {
				return new UsernamePasswordAuthenticationToken(email, null, emptyList());
			}
		}
		return null;
	}

}
