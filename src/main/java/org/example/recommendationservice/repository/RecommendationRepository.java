package org.example.recommendationservice.repository;

import org.example.modelproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Product, Long> {

}