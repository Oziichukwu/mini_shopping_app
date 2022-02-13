package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.dtos.response.CartDto;
import com.example.minishoppingoutlet.data.models.Cart;
import com.example.minishoppingoutlet.data.repositories.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Cart createCart() {
        return null;
    }

    @Override
    public BigDecimal calculateCartTotal(String cartId) {
        return null;
    }

    @Override
    public void addItemToCart(String productId, String cartId, int quantity) {

    }

    @Override
    public void removeItemFromCart(String cartId, String productId) {

    }

    @Override
    public CartDto findCartById(String cartId) {
        return null;
    }

    @Override
    public void reduceCartItemQuantity(String cartId, String productId, int quantity) {

    }
}
