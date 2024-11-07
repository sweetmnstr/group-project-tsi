package com.example.chocolate.controllers;

import com.example.chocolate.entities.Store;
import com.example.chocolate.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "StoreController", description = "StoreController endpoints")
@RestController
@RequestMapping("/api/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @Operation(summary = "Get all stores", description = "Get a list of all stores")
    @GetMapping
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @Operation(summary = "Get store by ID", description = "Get a store by its ID")
    @GetMapping("/{id}")
    public Store getStoreById(@PathVariable Long id) {
        return storeService.getStoreById(id);
    }

    @Operation(summary = "Create a new store", description = "Create a new store")
    @PostMapping
    public Store createStore(@RequestBody Store store) {
        return storeService.saveStore(store);
    }

    @Operation(summary = "Update a store", description = "Update an existing store by its ID")
    @PutMapping("/{id}")
    public Store updateStore(@PathVariable Long id, @RequestBody Store storeDetails) {
        Store store = storeService.getStoreById(id);
        store.setName(storeDetails.getName());
        store.setLocation(storeDetails.getLocation());
        store.setContactInfo(storeDetails.getContactInfo());
        return storeService.saveStore(store);
    }

    @Operation(summary = "Delete a store", description = "Delete a store by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Search stores by name", description = "Search stores by name")
    @GetMapping("/search")
    public List<Store> searchStoresByName(@RequestParam String name) {
        return storeService.searchStoresByName(name);
    }

    @Operation(summary = "Sort stores by name", description = "Sort stores by name")
    @GetMapping("/sort")
    public List<Store> sortStoresByName() {
        return storeService.sortStoresByName();
    }
}
