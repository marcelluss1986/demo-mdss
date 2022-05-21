package com.mdss.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdss.demo.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
