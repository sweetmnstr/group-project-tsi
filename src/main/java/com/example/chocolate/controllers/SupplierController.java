package com.example.chocolate.controllers;

import com.example.chocolate.entities.Supplier;
import com.example.chocolate.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "SupplierController", description = "SupplierController endpoints")
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @Operation(summary = "Get all suppliers", description = "Get a list of all suppliers")
    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @Operation(summary = "Get supplier by ID", description = "Get a supplier by its ID")
    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @Operation(summary = "Create a new supplier", description = "Create a new supplier")
    @PostMapping
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierService.saveSupplier(supplier);
    }

    @Operation(summary = "Update a supplier", description = "Update an existing supplier")
    @PutMapping("/{id}")
    public Supplier updateSupplier(@PathVariable Long id, @RequestBody Supplier supplierDetails) {
        Supplier supplier = supplierService.getSupplierById(id);
        supplier.setName(supplierDetails.getName());
        supplier.setContactInfo(supplierDetails.getContactInfo());
        return supplierService.saveSupplier(supplier);
    }

    @Operation(summary = "Delete a supplier", description = "Delete an existing supplier")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Search suppliers by name", description = "Search suppliers by name")
    @GetMapping("/search")
    public List<Supplier> searchSuppliersByName(@RequestParam String name) {
        return supplierService.searchSuppliersByName(name);
    }
}
