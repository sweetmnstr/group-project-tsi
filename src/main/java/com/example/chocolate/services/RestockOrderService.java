package com.example.chocolate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.chocolate.entities.RawMaterial;
import com.example.chocolate.entities.RestockOrder;
import com.example.chocolate.repositories.RawMaterialRepository;
import com.example.chocolate.repositories.RestockOrderRepository;
import java.util.List;

@Service
public class RestockOrderService {

    @Scheduled(fixedRate = 86400000) // Runs once every 24 hours (adjust as needed)
    public void scheduledRestockCheck() {
        checkAndGenerateRestockOrders();
    }

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    @Autowired
    private RestockOrderRepository restockOrderRepository;

    public void checkAndGenerateRestockOrders() {
        List<RawMaterial> lowStockMaterials = rawMaterialRepository.findAll().stream()
                .filter(material -> material.getQuantity() < material.getRestockThreshold())
                .toList();

        for (RawMaterial material : lowStockMaterials) {
            RestockOrder restockOrder = new RestockOrder(material, calculateRestockQuantity(material));
            restockOrderRepository.save(restockOrder);
            System.out.println("Restock order generated for: " + material.getName());
        }
    }

    private int calculateRestockQuantity(RawMaterial material) {
        // Logic to determine how much to restock, e.g., restock to threshold level
        return material.getRestockThreshold() - material.getQuantity();
    }
}
