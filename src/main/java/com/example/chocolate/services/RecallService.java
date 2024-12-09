package com.example.chocolate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.chocolate.entities.FinishedProduct;
import com.example.chocolate.entities.Recall;
import com.example.chocolate.repositories.FinishedProductRepository;
import com.example.chocolate.repositories.RecallRepository;
import com.example.chocolate.exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
public class RecallService {

    @Autowired
    private RecallRepository recallRepository;

    @Autowired
    private FinishedProductRepository finishedProductRepository;

    public Recall createRecall(Long finishedProductId, String reason) {
        FinishedProduct product = finishedProductRepository.findById(finishedProductId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Finished product not found with id " + finishedProductId));

        Recall recall = new Recall();
        recall.setFinishedProduct(product);
        recall.setReason(reason);
        recall.setRecallDate(LocalDate.now());

        return recallRepository.save(recall);
    }

    public List<Recall> getRecallHistory(Long finishedProductId) {
        return recallRepository.findByFinishedProductId(finishedProductId);
    }
}
