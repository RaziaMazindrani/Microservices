package com.razia.paymentservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PAYMENT_TB")
public class Payment {

    @Id
    @GeneratedValue
    private int paymentId;
    private String paymentStatus;
    private String trasactionId;
    //order service
    private int orderId;
    private double price;

}
