package com.example.chocolate.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class FinishedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int quantity;
    private LocalDate productionDate;
    private LocalDate expiryDate;

    @ManyToMany
    @JoinTable(name = "product_raw_material", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "raw_material_id"))
    private Set<RawMaterial> rawMaterials;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Set<RawMaterial> getRawMaterials() {
        return rawMaterials;
    }

    public void setRawMaterials(Set<RawMaterial> rawMaterials) {
        this.rawMaterials = rawMaterials;
    }
}
