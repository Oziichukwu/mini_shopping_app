package com.example.minishoppingoutlet.services;


import com.example.minishoppingoutlet.data.dtos.response.CartDto;
import com.example.minishoppingoutlet.data.models.Cart;

import java.math.BigDecimal;

public interface CartService {

    Cart createCart();

    BigDecimal calculateCartTotal(String cartId );

    void addItemToCart(String productId,String cartId, int quantity);

    void removeItemFromCart(String cartId, String productId);

    CartDto findCartById(String cartId);

    void reduceCartItemQuantity(String cartId, String productId, int quantity);
}
