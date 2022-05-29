package com.mdss.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mdss.demo.dto.CreateUserDTO;


@Service
public class UserDetailServiceImp implements UserDetailsService{
	
	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
	{
		CreateUserDTO currentUser = service.findUserByEmail(email);
		UserDetails user = new org.springframework.security.core
		          .userdetails.User(email, currentUser.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
		return user;
	}
	
	
		
	

	
}
