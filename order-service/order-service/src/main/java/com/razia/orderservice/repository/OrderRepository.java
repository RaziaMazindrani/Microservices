package com.razia.orderservice.repository;

import com.razia.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
