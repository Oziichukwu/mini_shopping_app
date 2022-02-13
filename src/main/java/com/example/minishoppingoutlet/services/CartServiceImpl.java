package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.dtos.response.CartDto;
import com.example.minishoppingoutlet.data.models.Cart;
import com.example.minishoppingoutlet.data.repositories.CartRepository;
import com.example.minishoppingoutlet.exceptions.CartException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductService productService;

    @Override
    public Cart createCart() {
        return createNewCart();
    }

    private Cart createNewCart() {
        return cartRepository.save(new Cart());
    }

    @Override
    public BigDecimal calculateCartTotal(String cartId)throws CartException {
        Cart cart = findById(cartId);
        BigDecimal total = BigDecimal.ZERO;
        return null;
    }

    private Cart findById(String cartId) throws CartException {
        return cartRepository.findById(cartId).orElseThrow(()->
                new CartException("No cart found with id" + cartId));
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
