package com.razia.paymentservice.repository;

import com.razia.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    Payment findByOrderId(int orderId);
}
