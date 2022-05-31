package com.mdss.demo.dto;

import java.util.UUID;

import com.mdss.demo.entities.User;

public class UserDTO {
	private long id;
	private String email;
	private UUID uuid;
	
	public UserDTO() {
		
	}
	
	public UserDTO(User obj) {
		this.id = obj.getId();
		this.email = obj.getEmail();
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
}
