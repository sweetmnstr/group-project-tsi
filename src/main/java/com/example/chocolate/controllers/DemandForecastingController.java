package com.example.chocolate.controllers;

import com.example.chocolate.services.DemandForecastingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;

@Tag(name = "DemandForecastingController", description = "DemandForecastingController endpoints")
@RestController
@RequestMapping("/api/forecast")
public class DemandForecastingController {

    @Autowired
    private DemandForecastingService demandForecastingService;

    @Operation(summary = "Get demand forecast", description = "Get demand forecast for the next 7 days")
    @GetMapping("/demand")
    public ResponseEntity<Map<Long, Integer>> getDemandForecast() {
        Map<Long, Integer> demandForecast = demandForecastingService.getDemandForecast();
        return ResponseEntity.ok(demandForecast);
    }
}
