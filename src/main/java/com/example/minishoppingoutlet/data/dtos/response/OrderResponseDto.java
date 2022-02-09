package com.example.minishoppingoutlet.data.dtos.response;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class OrderResponseDto {

    @NotBlank
    private String orderId;

    @NotBlank
    private BigDecimal orderTotal;
}
