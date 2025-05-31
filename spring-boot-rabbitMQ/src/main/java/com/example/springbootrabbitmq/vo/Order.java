package com.example.springbootrabbitmq.vo;

import lombok.Data;

@Data
public class Order {
    private String userId;
    private String orderId;
    private String title;
}
