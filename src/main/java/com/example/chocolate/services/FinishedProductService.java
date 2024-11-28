package com.example.chocolate.services;

import com.example.chocolate.entities.FinishedProduct;
import com.example.chocolate.exceptions.ResourceNotFoundException;
import com.example.chocolate.repositories.FinishedProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FinishedProductService {
    @Autowired
    private FinishedProductRepository finishedProductRepository;
    private static final int QUANTITY_THRESHOLD = 30;

    public List<FinishedProduct> getAllFinishedProducts() {
        return finishedProductRepository.findAll();
    }

    private static final Logger logger = LoggerFactory.getLogger(FinishedProductService.class);

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkAndNotifyExpiredProducts() {
        LocalDate today = LocalDate.now();
        List<FinishedProduct> expiredProducts = finishedProductRepository.findByExpiryDateBefore(today);

        if (!expiredProducts.isEmpty()) {
            for (FinishedProduct product : expiredProducts) {
                logger.warn(
                        "Expired Product Alert: Product {} has expired. Expiry Date: {}",
                        product.getName(), product.getExpiryDate());
            }
        }
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
