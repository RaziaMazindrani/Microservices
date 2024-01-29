package com.razia.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.razia.orderservice.common.Payment;
import com.razia.orderservice.common.TransactionRequest;
import com.razia.orderservice.common.TransactionResponse;
import com.razia.orderservice.entity.Order;
import com.razia.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
@RefreshScope   //for cloud config
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    @Lazy   //for cloud config
    private RestTemplate restTemplate;

    //we are getting this key value from common config file in git
    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String PAYMENT_SERVICE_URL;

    private Logger log= LoggerFactory.getLogger(OrderService.class);

    public TransactionResponse saveOrder(TransactionRequest transactionRequest) throws JsonProcessingException {
        String response="";
        Order order = transactionRequest.getOrder();
        Payment payment = transactionRequest.getPayment();
        payment.setOrderId(order.getId());
        payment.setPrice(order.getPrice());

        log.info("OrderService request : {}",new ObjectMapper().writeValueAsString(transactionRequest));

        //rest api call for payment api
        Payment paymentResponse=restTemplate.postForObject(PAYMENT_SERVICE_URL,payment, Payment.class);

        log.info("PaymentService response from OrderService rest call : {}",new ObjectMapper().writeValueAsString(paymentResponse));
        response=paymentResponse.getPaymentStatus().equals("success")?"Payment processed successfully, order place":"Payment Failed";

        repository.save(order);

        return new TransactionResponse(order,paymentResponse.getPrice(),paymentResponse.getTrasactionId(),response);
    }
}
