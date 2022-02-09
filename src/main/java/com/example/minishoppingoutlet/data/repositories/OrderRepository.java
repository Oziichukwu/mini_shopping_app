package com.example.minishoppingoutlet.data.repositories;

import com.example.minishoppingoutlet.data.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
    void findByOrderId(String orderId);
}
