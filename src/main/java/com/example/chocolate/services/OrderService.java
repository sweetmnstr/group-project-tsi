package com.example.chocolate.services;

import com.example.chocolate.entities.Order;
import com.example.chocolate.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import com.example.chocolate.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getOrdersByStoreId(Long storeId) {
        return orderRepository.findByStoreId(storeId);
    }

    public List<Order> getOrdersByProductId(Long productId) {
        return orderRepository.findByProductId(productId);
    }

    public List<Order> getOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }

    public List<Order> sortOrdersByOrderDate() {
        return orderRepository.findAll(Sort.by(Sort.Direction.ASC, "orderDate"));
    }

    public List<Order> sortOrdersByDeliveryDate() {
        return orderRepository.findAll(Sort.by(Sort.Direction.ASC, "deliveryDate"));
    }
}
