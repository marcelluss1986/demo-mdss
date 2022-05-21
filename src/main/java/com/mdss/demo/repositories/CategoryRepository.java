package com.mdss.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdss.demo.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{
	
	
}
