package org.example.recommendationservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.modelproject.Product;
import org.example.recommendationservice.dto.ProductDTO;
import org.example.recommendationservice.repository.RecommendationRepository;
import org.example.recommendationservice.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RecommendationServiceImpl implements RecommendationService {
    private final RecommendationRepository recommendationRepository;

    @Autowired
    public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    // get product recommended
    @Override
    public ProductDTO getRecommendation(long customerId) {
        log.info("RecommendationServiceImpl - Method getRecommendation");
        List<Product> products = recommendationRepository.findAll();
        int productsSize = products.size();
        log.info("RecommendationServiceImpl - Number of products in DB: "+productsSize);
        Product productRecommended = null;
        int productIndex = (int) (Math.random() * productsSize);
        int searchProduct = 0;
        for (Product product : products) {
            if (searchProduct < productIndex){
                searchProduct++;
            } else {
                productRecommended = product;
                break;
            }
        }
        log.info("RecommendationServiceImpl - Found getRecommendation: "+productRecommended);
        return mapProductToDTO(productRecommended);
    }

    static ProductDTO mapProductToDTO (Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

}
