package com.mdss.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdss.demo.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
	
	
}
