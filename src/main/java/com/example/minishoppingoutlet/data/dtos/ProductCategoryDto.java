package com.example.minishoppingoutlet.data.dtos;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;

@Data
public class ProductCategoryDto {

    private String id;

    @NotBlank(message = "Category name cannot be blank")
    private String name;
}
