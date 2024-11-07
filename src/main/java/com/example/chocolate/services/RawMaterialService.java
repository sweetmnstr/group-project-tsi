package com.example.chocolate.services;

import com.example.chocolate.entities.*;
import com.example.chocolate.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import com.example.chocolate.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RawMaterialService {
    @Autowired
    private RawMaterialRepository rawMaterialRepository;

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