package org.example.recommendationservice.service;

import org.example.modelproject.dto.ProductRecommendedDTO;

public interface RecommendationService {
    ProductRecommendedDTO getRecommendation(long customerId);
}
