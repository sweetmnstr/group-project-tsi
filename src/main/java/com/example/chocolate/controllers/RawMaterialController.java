package com.example.chocolate.controllers;

import com.example.chocolate.entities.*;
import com.example.chocolate.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "RawMaterialController", description = "RawMaterialController endpoints")
@RestController
@RequestMapping("/api/rawmaterials")
public class RawMaterialController {
    @Autowired
    private RawMaterialService rawMaterialService;

    @Operation(summary = "Get all raw materials", description = "Get a list of all raw materials")
    @GetMapping
    public List<RawMaterial> getAllRawMaterials() {
        return rawMaterialService.getAllRawMaterials();
    }

    @Operation(summary = "Get raw material by ID", description = "Get a raw material by its ID")
    @GetMapping("/{id}")
    public RawMaterial getRawMaterialById(@PathVariable Long id) {
        return rawMaterialService.getRawMaterialById(id);
    }

    @Operation(summary = "Create raw material", description = "Create a new raw material")
    @PostMapping
    public RawMaterial createRawMaterial(@RequestBody RawMaterial rawMaterial) {
        return rawMaterialService.saveRawMaterial(rawMaterial);
    }

    @Operation(summary = "Update raw material", description = "Update an existing raw material")
    @PutMapping("/{id}")
    public RawMaterial updateRawMaterial(@PathVariable Long id, @RequestBody RawMaterial rawMaterialDetails) {
        RawMaterial rawMaterial = rawMaterialService.getRawMaterialById(id);
        rawMaterial.setName(rawMaterialDetails.getName());
        rawMaterial.setQuantity(rawMaterialDetails.getQuantity());
        rawMaterial.setSupplier(rawMaterialDetails.getSupplier());
        return rawMaterialService.saveRawMaterial(rawMaterial);
    }

    @Operation(summary = "Delete raw material", description = "Delete a raw material by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRawMaterial(@PathVariable Long id) {
        rawMaterialService.deleteRawMaterial(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Search raw materials by name", description = "Search raw materials by name")
    @GetMapping("/search")
    public List<RawMaterial> searchRawMaterialsByName(@RequestParam String name) {
        return rawMaterialService.searchRawMaterialsByName(name);
    }

    @Operation(summary = "Filter raw materials by quantity", description = "Filter raw materials by quantity")
    @GetMapping("/filter")
    public List<RawMaterial> filterRawMaterialsByQuantity(@RequestParam int quantity) {
        return rawMaterialService.filterRawMaterialsByQuantity(quantity);
    }

    @Operation(summary = "Sort raw materials by name", description = "Sort raw materials by name")
    @GetMapping("/sort")
    public List<RawMaterial> sortRawMaterialsByName() {
        return rawMaterialService.sortRawMaterialsByName();
    }
}
