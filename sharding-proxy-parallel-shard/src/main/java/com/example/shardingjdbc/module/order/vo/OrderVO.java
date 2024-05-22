package com.example.shardingjdbc.module.order.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderVO {
    private String orderNO;
    private BigDecimal amount;
}
