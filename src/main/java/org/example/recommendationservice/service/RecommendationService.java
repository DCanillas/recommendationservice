package org.example.recommendationservice.service;

import org.example.recommendationservice.dto.ProductDTO;

public interface RecommendationService {
    ProductDTO getRecommendation(long customerId);
}
