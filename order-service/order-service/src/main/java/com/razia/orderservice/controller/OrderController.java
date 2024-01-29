package com.razia.orderservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.razia.orderservice.common.TransactionRequest;
import com.razia.orderservice.common.TransactionResponse;
import com.razia.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest) throws JsonProcessingException {
        return service.saveOrder(transactionRequest);
    }
}
