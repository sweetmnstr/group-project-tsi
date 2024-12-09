package com.example.chocolate.services;

import com.example.chocolate.entities.FinishedProduct;
import com.example.chocolate.entities.Order;
import com.example.chocolate.repositories.FinishedProductRepository;
import com.example.chocolate.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.example.chocolate.exceptions.InsufficientStockException;
import com.example.chocolate.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private FinishedProductRepository finishedProductRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    public Order saveOrder(Order order) {

        FinishedProduct product = finishedProductRepository.findById(order.getProduct().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with ID: " + order.getProduct().getId()));
        if (order.getQuantity() > product.getQuantity()) {
            throw new InsufficientStockException(
                    "Insufficient stock for product: " + product.getName() +
                            ". Available: " + product.getQuantity() + ", Requested: " + order.getQuantity());
        }
        product.setQuantity(product.getQuantity() - order.getQuantity());
        finishedProductRepository.save(product);

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
