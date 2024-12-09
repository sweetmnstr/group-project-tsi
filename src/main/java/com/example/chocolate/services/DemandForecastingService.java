package com.example.chocolate.services;

import com.example.chocolate.entities.FinishedProduct;
import com.example.chocolate.repositories.FinishedProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class DemandForecastingService {

    @Autowired
    private FinishedProductRepository finishedProductRepository;

    public Map<Long, Integer> getDemandForecast() {
        List<FinishedProduct> products = finishedProductRepository.findAll();
        Map<Long, Integer> demandForecast = new HashMap<>();

        for (FinishedProduct product : products) {
            int forecastedDemand = calculateSeasonalDemand(product.getSalesHistory());
            demandForecast.put(product.getId(), forecastedDemand);
        }
        return demandForecast;
    }

    private int calculateSeasonalDemand(Map<Integer, Integer> salesHistory) {
        if (salesHistory.isEmpty()) {
            return 0; // No historical data, return zero
        }

        int currentMonth = LocalDate.now().getMonthValue();
        int seasonalMultiplier = getSeasonalMultiplier(currentMonth);

        // Calculate average sales
        int totalSales = salesHistory.values().stream().mapToInt(Integer::intValue).sum();
        int averageSales = totalSales / salesHistory.size();

        // Apply seasonal adjustment
        return averageSales * seasonalMultiplier;
    }

    private int getSeasonalMultiplier(int month) {
        // Example seasonal multipliers (customize as needed)
        Map<Integer, Integer> seasonalMultipliers = Map.of(
                12, 2, // December (high demand)
                6, 2, // June (high demand)
                1, 1, // January (normal demand)
                2, 1, // February (normal demand)
                11, 1 // November (normal demand)
        );
        return seasonalMultipliers.getOrDefault(month, 1);
    }
}
