package com.example.chocolate.repositories;

import com.example.chocolate.entities.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
    List<RawMaterial> findByNameContaining(String name);

    List<RawMaterial> findByQuantityGreaterThanEqual(int quantity);
}