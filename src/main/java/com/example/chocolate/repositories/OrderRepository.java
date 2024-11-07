package com.example.chocolate.repositories;

import com.example.chocolate.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStoreId(Long storeId);

    List<Order> findByProductId(Long productId);

    List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);
}
