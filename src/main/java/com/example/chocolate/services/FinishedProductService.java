package com.example.chocolate.services;

import com.example.chocolate.entities.FinishedProduct;
import com.example.chocolate.exceptions.ResourceNotFoundException;
import com.example.chocolate.repositories.FinishedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinishedProductService {
    @Autowired
    private FinishedProductRepository finishedProductRepository;

    public List<FinishedProduct> getAllFinishedProducts() {
        return finishedProductRepository.findAll();
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
