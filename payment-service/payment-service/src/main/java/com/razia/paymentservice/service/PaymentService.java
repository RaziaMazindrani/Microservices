package com.razia.paymentservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.razia.paymentservice.entity.Payment;
import com.razia.paymentservice.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    private Logger log= LoggerFactory.getLogger(PaymentService.class);

    public Payment savePayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTrasactionId(UUID.randomUUID().toString());
        log.info("PaymentService request : {}",new ObjectMapper().writeValueAsString(payment));
        return repository.save(payment);
    }

    public String paymentProcessing(){
        //ideally this should come from third party api like paypal or paytm
        return new Random().nextBoolean()?"success":"falied";
    }

    public Payment findPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {
        Payment payment=repository.findByOrderId(orderId);
        log.info("PaymentService findPaymentHistoryByOrderId : {}",new ObjectMapper().writeValueAsString(payment));
        return payment;
    }
}
