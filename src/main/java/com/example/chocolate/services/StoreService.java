package com.example.chocolate.services;

import com.example.chocolate.entities.Store;
import com.example.chocolate.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.chocolate.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Store not found"));
    }

    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }

    public List<Store> searchStoresByName(String name) {
        return storeRepository.findByNameContaining(name);
    }

    public List<Store> sortStoresByName() {
        return storeRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
}
