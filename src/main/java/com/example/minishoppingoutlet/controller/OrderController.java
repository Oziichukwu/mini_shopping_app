package com.example.minishoppingoutlet.controller;


import com.example.minishoppingoutlet.data.dtos.request.OrderRequestDto;
import com.example.minishoppingoutlet.data.dtos.response.ApiResponse;
import com.example.minishoppingoutlet.data.dtos.response.OrderResponseDto;
import com.example.minishoppingoutlet.exceptions.OrderException;
import com.example.minishoppingoutlet.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/miniStore/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<?> findOrderById(@PathVariable String orderId){
        try {
            OrderResponseDto foundOrder = orderService.findOrderById(orderId);
            return new ResponseEntity<>(foundOrder, HttpStatus.OK);

    }catch(OrderException e){
            return new ResponseEntity<>(new ApiResponse(false, "Order not found"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrderDetails(@PathVariable String orderId, @RequestBody OrderRequestDto orderRequestDto){

        try {
            OrderResponseDto updatedOrder = orderService.updateOrderDetails(orderId, orderRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedOrder);
        }catch (OrderException e){
            return new ResponseEntity<>(new ApiResponse(true, "Wrong credentials"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?>cancelByOrderId(@PathVariable String orderId){
        try {
            orderService.cancelOrderByOrderId(orderId);
            return new ResponseEntity<>(new ApiResponse(true, "Order Deleted Successfully"), HttpStatus.NO_CONTENT);

        }catch (OrderException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
