package org.example.recommendationservice.controller;

import org.example.modelproject.dto.ProductRecommendedDTO;
import org.example.recommendationservice.service.impl.RecommendationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.version}/recommendation")
public class RecommendationController {
    private final RecommendationServiceImpl recommendationService;

    @Autowired
    public RecommendationController(RecommendationServiceImpl recommendationService) {
        this.recommendationService = recommendationService;
    }

    // get product recommended
    @GetMapping("/{id}")
    public ResponseEntity<ProductRecommendedDTO> getRecommendation(@PathVariable(value = "id") long customerId) {
        return ResponseEntity.ok().body(recommendationService.getRecommendation(customerId));
    }
}
