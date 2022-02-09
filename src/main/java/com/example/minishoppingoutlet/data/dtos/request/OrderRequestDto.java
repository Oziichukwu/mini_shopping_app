package com.example.minishoppingoutlet.data.dtos.request;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class OrderRequestDto {

    private String orderId;

    private BigDecimal orderTotal;
}
