package com.mdss.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdss.demo.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	
}
