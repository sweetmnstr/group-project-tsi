package com.example.chocolate.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Recall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reason;
    private LocalDate recallDate;

    @ManyToOne
    @JoinColumn(name = "finished_product_id")
    private FinishedProduct finishedProduct;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getRecallDate() {
        return recallDate;
    }

    public void setRecallDate(LocalDate recallDate) {
        this.recallDate = recallDate;
    }

    public FinishedProduct getFinishedProduct() {
        return finishedProduct;
    }

    public void setFinishedProduct(FinishedProduct finishedProduct) {
        this.finishedProduct = finishedProduct;
    }

}
