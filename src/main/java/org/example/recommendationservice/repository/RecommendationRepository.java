package org.example.recommendationservice.repository;

import org.example.modelproject.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Product, Long> {

}