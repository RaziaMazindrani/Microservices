package com.razia.orderservice.common;

import com.razia.orderservice.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Order order;
    private double price;
    private String transactionId;
    private String message;
}
