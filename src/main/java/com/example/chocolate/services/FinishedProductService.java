package com.example.chocolate.services;

import com.example.chocolate.entities.FinishedProduct;
import com.example.chocolate.entities.RawMaterial;
import com.example.chocolate.exceptions.ResourceNotFoundException;
import com.example.chocolate.repositories.FinishedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.chocolate.repositories.RawMaterialRepository;
import java.time.LocalDate;
import java.util.List;

@Service
public class FinishedProductService {

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    @Autowired
    private FinishedProductRepository finishedProductRepository;

    public void produceFinishedProduct(Long rawMaterialId, int quantity) {

        // Use rawMaterialRepository instance to call findById
        RawMaterial rawMaterial = rawMaterialRepository.findById(rawMaterialId)
                .orElseThrow(() -> new IllegalArgumentException("RawMaterial not found with ID: " + rawMaterialId));

        // Validate if raw material is expired
        if (rawMaterial.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot use expired raw material: " + rawMaterial.getName());
        }

        // Check for sufficient quantity
        if (rawMaterial.getQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient quantity for raw material: " + rawMaterial.getName());
        }

        // Deduct the quantity used in production
        rawMaterial.setQuantity(rawMaterial.getQuantity() - quantity);
        rawMaterialRepository.save(rawMaterial);
    }

    private static final int QUANTITY_THRESHOLD = 30;

    public List<FinishedProduct> getAllFinishedProducts() {
        return finishedProductRepository.findAll();
    }

    public FinishedProduct adjustQuantity(Long id, int quantityAdjustment) {
        FinishedProduct product = finishedProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        int newQuantity = product.getQuantity() + quantityAdjustment;
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        product.setQuantity(newQuantity);
        return finishedProductRepository.save(product);
    }

    @SuppressWarnings("unused")
    private void triggerQuantityAlert(FinishedProduct product) {
        System.out.println("Alert: Product " + product.getName() + " (ID: " + product.getId()
                + ") has fallen below the quantity threshold of " + QUANTITY_THRESHOLD + ".");
    }

    public FinishedProduct getFinishedProductById(Long id) {
        return finishedProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FinishedProduct not found"));
    }

    public FinishedProduct saveFinishedProduct(FinishedProduct finishedProduct) {
        return finishedProductRepository.save(finishedProduct);
    }

    public void deleteFinishedProduct(Long id) {
        finishedProductRepository.deleteById(id);
    }

    public List<FinishedProduct> searchFinishedProductsByName(String name) {
        return finishedProductRepository.findByNameContaining(name);
    }

    public List<FinishedProduct> filterFinishedProductsByQuantity(int quantity) {
        return finishedProductRepository.findByQuantityGreaterThanEqual(quantity);
    }

    public List<FinishedProduct> sortFinishedProductsByName() {
        return finishedProductRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

}
