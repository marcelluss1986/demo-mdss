package com.mdss.demo.web.config;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdss.demo.dto.CreateUserDTO;
import com.mdss.demo.services.AuthenticationServices;

public class LoginFilter extends AbstractAuthenticationProcessingFilter{
	public LoginFilter(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
		
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {

		CreateUserDTO dto = new ObjectMapper()
				.readValue(req.getInputStream(), CreateUserDTO.class);
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
				dto.getEmail(), dto.getPassword(), Collections.emptyList()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, 
			HttpServletResponse res, FilterChain chain, Authentication authResult) 
			throws IOException, ServletException {
			AuthenticationServices.addToken(res, authResult.getName()); 
	}
	

}
