package com.mdss.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdss.demo.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{
	
	
}
