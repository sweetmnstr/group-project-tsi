package com.example.chocolate.controllers;

import com.example.chocolate.entities.Order;
import com.example.chocolate.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "OrderController", description = "OrderController endpoints")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Operation(summary = "Get all orders", description = "Get a list of all orders")
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Operation(summary = "Get order by ID", description = "Get an order by its ID")
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @Operation(summary = "Create order", description = "Create a new order")
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.createOrder(order);
        return ResponseEntity.ok(savedOrder);
    }

    @Operation(summary = "Update order", description = "Update an existing order")
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Order order = orderService.getOrderById(id);
        order.setQuantity(orderDetails.getQuantity());
        order.setOrderDate(orderDetails.getOrderDate());
        order.setDeliveryDate(orderDetails.getDeliveryDate());
        order.setStore(orderDetails.getStore());
        order.setProduct(orderDetails.getProduct());
        return orderService.saveOrder(order);
    }

    @Operation(summary = "Delete order", description = "Delete an order by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get orders by store ID", description = "Get a list of orders by store ID")
    @GetMapping("/store/{storeId}")
    public List<Order> getOrdersByStoreId(@PathVariable Long storeId) {
        return orderService.getOrdersByStoreId(storeId);
    }

    @Operation(summary = "Get orders by product ID", description = "Get a list of orders by product ID")
    @GetMapping("/product/{productId}")
    public List<Order> getOrdersByProductId(@PathVariable Long productId) {
        return orderService.getOrdersByProductId(productId);
    }

    @Operation(summary = "Get orders by date range", description = "Get a list of orders within a specified date range")
    @GetMapping("/date-range")
    public List<Order> getOrdersByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return orderService.getOrdersByDateRange(startDate, endDate);
    }

    @Operation(summary = "Sort orders by order date", description = "Sort orders by order date in ascending order")
    @GetMapping("/sort/order-date")
    public List<Order> sortOrdersByOrderDate() {
        return orderService.sortOrdersByOrderDate();
    }

    @Operation(summary = "Sort orders by delivery date", description = "Sort orders by delivery date in ascending order")
    @GetMapping("/sort/delivery-date")
    public List<Order> sortOrdersByDeliveryDate() {
        return orderService.sortOrdersByDeliveryDate();
    }
}
