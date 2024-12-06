package com.example.chocolate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDate;

@Entity
public class RestockOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "raw_material_id")
    private RawMaterial rawMaterial;

    private int quantityToRestock;
    private LocalDate orderDate;

    // Constructors, Getters, and Setters
    public RestockOrder() {
    }

    public RestockOrder(RawMaterial rawMaterial, int quantityToRestock) {
        this.rawMaterial = rawMaterial;
        this.quantityToRestock = quantityToRestock;
        this.orderDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public int getQuantityToRestock() {
        return quantityToRestock;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}
