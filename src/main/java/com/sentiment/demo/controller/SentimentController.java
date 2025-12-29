package com.sentiment.demo.controller;

import com.sentiment.demo.dto.SentimentRequest;
import com.sentiment.demo.dto.SentimentResponse;
import com.sentiment.demo.service.SentimentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SentimentController {

    private final SentimentService sentimentService;
    public ResponseEntity<SentimentResponse> analizar(@Valid @RequestBody SentimentRequest request) {
        requestCounter.incrementAndGet();
        SentimentResponse response = sentimentService.predict(request.text().trim());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SentimentResponse> analizar(@Valid @RequestBody SentimentRequest request) {

        // Ya viene validado por @Valid: min 5, max 2000, not blank
        SentimentResponse response = sentimentService.predict(request.text().trim());
        return ResponseEntity.ok(response);
    }
}