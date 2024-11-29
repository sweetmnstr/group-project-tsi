package com.example.chocolate.services;

import com.example.chocolate.entities.FinishedProduct;
import com.example.chocolate.entities.RawMaterial;
import com.example.chocolate.exceptions.ResourceNotFoundException;
import com.example.chocolate.repositories.FinishedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.chocolate.services.RawMaterialService;

@Service
public class FinishedProductService {
    @Autowired
    private FinishedProductRepository finishedProductRepository;
    private static final int QUANTITY_THRESHOLD = 30;
    private static final double LABOR_COST_PER_UNIT = 5.0;

    public void calculateAndSetCost(FinishedProduct product) {
        // Calculate the total cost of raw materials
        double rawMaterialCost = product.getRawMaterials().stream()
                .mapToDouble(RawMaterial::getPrice) // Correct method reference
                .sum();

        // Calculate labor cost
        double laborCost = LABOR_COST_PER_UNIT * product.getQuantity();

        // Set the total cost
        product.setCost(rawMaterialCost + laborCost);
    }

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
