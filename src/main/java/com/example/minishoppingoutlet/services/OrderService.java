package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.dtos.request.OrderRequestDto;
import com.example.minishoppingoutlet.data.dtos.response.OrderResponseDto;

public interface OrderService {

    OrderResponseDto findOrderById(String orderId);

    OrderResponseDto updateOrderDetails(String orderId, OrderRequestDto orderToUpdate);

    void cancelOrderByOrderId(String orderId);

}
