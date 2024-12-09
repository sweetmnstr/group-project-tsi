package com.example.chocolate.controllers;

import com.example.chocolate.entities.*;
import com.example.chocolate.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "RecallController", description = "RecallController endpoints")
@RequestMapping("/api/recalls")
public class RecallController {

    @Autowired
    private RecallService recallService;

    @Operation(summary = "Create a recall", description = "Create a recall for a finished product")
    @PostMapping("/{finishedProductId}")
    public ResponseEntity<Recall> createRecall(
            @PathVariable Long finishedProductId,
            @RequestBody String reason) {
        Recall recall = recallService.createRecall(finishedProductId, reason);
        return ResponseEntity.ok(recall);
    }

    @Operation(summary = "Get recall history", description = "Get recall history for a finished product")
    @GetMapping("/{finishedProductId}")
    public ResponseEntity<List<Recall>> getRecallHistory(@PathVariable Long finishedProductId) {
        List<Recall> recalls = recallService.getRecallHistory(finishedProductId);
        return ResponseEntity.ok(recalls);
    }
}
