package com.example.chocolate.controllers;

import com.example.chocolate.entities.FinishedProduct;
import com.example.chocolate.services.FinishedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/finishedproducts")
@Tag(name = "FinishedProductController", description = "FinishedProductController endpoints")
public class FinishedProductController {
    @Autowired
    private FinishedProductService finishedProductService;

    @GetMapping
    @Operation(summary = "Get all finished products", description = "Get a list of all finished products")
    public List<FinishedProduct> getAllFinishedProducts() {
        return finishedProductService.getAllFinishedProducts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get finished product by ID", description = "Get a finished product by its ID")
    public FinishedProduct getFinishedProductById(@PathVariable Long id) {
        return finishedProductService.getFinishedProductById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new finished product", description = "Create a new finished product")
    public FinishedProduct createFinishedProduct(@RequestBody FinishedProduct finishedProduct) {
        return finishedProductService.saveFinishedProduct(finishedProduct);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a finished product", description = "Update an existing finished product")
    public FinishedProduct updateFinishedProduct(@PathVariable Long id,
            @RequestBody FinishedProduct finishedProductDetails) {
        FinishedProduct finishedProduct = finishedProductService.getFinishedProductById(id);
        finishedProduct.setName(finishedProductDetails.getName());
        finishedProduct.setQuantity(finishedProductDetails.getQuantity());
        finishedProduct.setProductionDate(finishedProductDetails.getProductionDate());
        finishedProduct.setExpiryDate(finishedProductDetails.getExpiryDate());
        return finishedProductService.saveFinishedProduct(finishedProduct);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a finished product", description = "Delete a finished product by its ID")
    public ResponseEntity<Void> deleteFinishedProduct(@PathVariable Long id) {
        finishedProductService.deleteFinishedProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search finished products by name", description = "Search finished products by name")
    public List<FinishedProduct> searchFinishedProductsByName(@RequestParam String name) {
        return finishedProductService.searchFinishedProductsByName(name);
    }

    @GetMapping("/filter")
    @Operation(summary = "Filter finished products by quantity", description = "Filter finished products by quantity")
    public List<FinishedProduct> filterFinishedProductsByQuantity(@RequestParam int quantity) {
        return finishedProductService.filterFinishedProductsByQuantity(quantity);
    }

    @GetMapping("/sort")
    @Operation(summary = "Sort finished products by name", description = "Sort finished products by name")
    public List<FinishedProduct> sortFinishedProductsByName() {
        return finishedProductService.sortFinishedProductsByName();
    }
}
