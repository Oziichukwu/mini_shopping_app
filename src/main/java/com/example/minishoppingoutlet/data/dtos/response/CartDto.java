package com.example.minishoppingoutlet.data.dtos.response;


import com.example.minishoppingoutlet.data.models.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    @NotBlank
    private String id;

    private BigDecimal total;

    private Map<String, Item> items;
}
