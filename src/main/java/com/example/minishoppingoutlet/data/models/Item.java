package com.example.minishoppingoutlet.data.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;


@Data
public class Item {

    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

    private int quantityAddedToCart = 1;

    private BigDecimal itemTotal;

    private BigDecimal updateProductAmount(int quantityAddedToCart){
        return product.getPrice().multiply(BigDecimal.valueOf(quantityAddedToCart));
    }

    public void increaseQuantity(int quantityAddedToCart){
        this.quantityAddedToCart += quantityAddedToCart;
        itemTotal = updateProductAmount(quantityAddedToCart);
    }

    public void decreaseQuantity(int quantityAddedToCart){
        this.quantityAddedToCart -= quantityAddedToCart;
        itemTotal = updateProductAmount(quantityAddedToCart);
    }

}
