package com.example.chocolate.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.Map;

@Entity
public class FinishedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int quantity;
    @Column(nullable = false)
    private LocalDate productionDate;

    @Column(nullable = false)
    private LocalDate expiryDate;
    private double cost;

    @ManyToMany
    @JoinTable(name = "product_raw_material", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "raw_material_id"))
    private Set<RawMaterial> rawMaterials;

    private int reorderLevel;

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

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

    public Set<RawMaterial> getRawMaterials() {
        return rawMaterials;
    }

    public void setRawMaterials(Set<RawMaterial> rawMaterials) {
        this.rawMaterials = rawMaterials;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        if (productionDate != null && expiryDate.isBefore(productionDate)) {
            throw new IllegalArgumentException("Expiry date must be after production date.");
        }
        this.expiryDate = expiryDate;
    }

    @OneToMany(mappedBy = "finishedProduct", cascade = CascadeType.ALL)
    private List<Recall> recalls = new ArrayList<>();

    // Getters and Setters
    public List<Recall> getRecalls() {
        return recalls;
    }

    public void setRecalls(List<Recall> recalls) {
        this.recalls = recalls;
    }

    @ElementCollection
    @CollectionTable(name = "finished_product_sales_history", joinColumns = @JoinColumn(name = "finished_product_id"))
    @MapKeyColumn(name = "month")
    @Column(name = "sales")
    private Map<Integer, Integer> salesHistory = new HashMap<>(); // Month -> Sales

    // Getters and Setters
    public Map<Integer, Integer> getSalesHistory() {
        return salesHistory;
    }

    public void setSalesHistory(Map<Integer, Integer> salesHistory) {
        this.salesHistory = salesHistory;
    }
}
