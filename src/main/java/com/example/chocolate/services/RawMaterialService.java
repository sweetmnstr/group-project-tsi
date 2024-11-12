package com.example.chocolate.services;

import com.example.chocolate.entities.*;
import com.example.chocolate.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import com.example.chocolate.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RawMaterialService {
    @Autowired
    private RawMaterialRepository rawMaterialRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    public List<RawMaterial> getAllRawMaterials() {
        return rawMaterialRepository.findAll();
    }

    public RawMaterial getRawMaterialById(Long id) {
        return rawMaterialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RawMaterial not found"));
    }

    public RawMaterial saveRawMaterial(RawMaterial rawMaterial) {
        return rawMaterialRepository.save(rawMaterial);
    }

    public List<RawMaterial> addRawMaterialsBatch(List<RawMaterial> rawMaterials) {
        List<RawMaterial> savedMaterials = new ArrayList<>();

        for (RawMaterial material : rawMaterials) {
            Supplier supplier = supplierRepository.findById(material.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Supplier not found with id " + material.getSupplierId()));

            RawMaterial rawMaterial = new RawMaterial();
            rawMaterial.setName(material.getName());
            rawMaterial.setQuantity(material.getQuantity());
            rawMaterial.setSupplier(supplier);

            savedMaterials.add(rawMaterialRepository.save(rawMaterial));
        }
        return savedMaterials;
    }

    public void deleteRawMaterial(Long id) {
        rawMaterialRepository.deleteById(id);
    }

    public List<RawMaterial> searchRawMaterialsByName(String name) {
        return rawMaterialRepository.findByNameContaining(name);
    }

    public List<RawMaterial> filterRawMaterialsByQuantity(int quantity) {
        return rawMaterialRepository.findByQuantityGreaterThanEqual(quantity);
    }

    public List<RawMaterial> sortRawMaterialsByName() {
        return rawMaterialRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
}