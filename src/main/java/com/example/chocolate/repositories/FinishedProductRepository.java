package com.example.chocolate.repositories;

import com.example.chocolate.entities.FinishedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinishedProductRepository extends JpaRepository<FinishedProduct, Long> {
    List<FinishedProduct> findByNameContaining(String name);

    List<FinishedProduct> findByQuantityGreaterThanEqual(int quantity);

    List<FinishedProduct> findByExpiryDateBefore(LocalDate date);
}
