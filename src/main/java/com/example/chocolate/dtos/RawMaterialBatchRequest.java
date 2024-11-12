package com.example.chocolate.dtos;

import java.util.List;

import com.example.chocolate.entities.RawMaterial;

public class RawMaterialBatchRequest {
    private List<RawMaterial> rawMaterials;

    // Getters and Setters

    public List<RawMaterial> getRawMaterials() {
        return rawMaterials;
    }

    public void setRawMaterials(List<RawMaterial> rawMaterials) {
        this.rawMaterials = rawMaterials;
    }
}
