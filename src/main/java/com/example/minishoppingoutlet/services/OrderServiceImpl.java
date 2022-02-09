package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.dtos.request.OrderRequestDto;
import com.example.minishoppingoutlet.data.dtos.response.OrderResponseDto;
import com.example.minishoppingoutlet.data.models.Order;
import com.example.minishoppingoutlet.data.repositories.OrderRepository;
import com.example.minishoppingoutlet.exceptions.OrderException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderResponseDto findOrderById(String orderId) {

        Order order = findByOrderId(orderId);
        return modelMapper.map(order, OrderResponseDto.class);
    }

    private Order findByOrderId(String orderId) {
        return orderRepository.findById(orderId).orElseThrow(()->
                new OrderException("No order found with that Id " + orderId));
    }

    @Override
    public OrderResponseDto updateOrderDetails(String orderId, OrderRequestDto orderRequestDto) {

        Order orderToUpdate = findByOrderId(orderId);
        modelMapper.map(orderRequestDto, orderToUpdate);
        orderRepository.save(orderToUpdate);

        return modelMapper.map(orderToUpdate, OrderResponseDto.class);

    }

    @Override
    public void cancelOrderByOrderId(String orderId) {

        Order orderToCancel = findByOrderId(orderId);

        deleteOrder(orderToCancel);
    }

    private void deleteOrder(Order orderToCancel) {
        orderRepository.delete(orderToCancel);
    }
}
