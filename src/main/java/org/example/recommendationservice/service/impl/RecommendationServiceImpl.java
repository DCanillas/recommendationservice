package org.example.recommendationservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.modelproject.dto.ProductRecommendedDTO;
import org.example.modelproject.model.Product;
import org.example.recommendationservice.repository.RecommendationRepository;
import org.example.recommendationservice.service.RecommendationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RecommendationServiceImpl implements RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RecommendationServiceImpl(RecommendationRepository recommendationRepository, ModelMapper modelMapper) {
        this.recommendationRepository = recommendationRepository;
        this.modelMapper = modelMapper;
    }

    // get product recommended
    @Override
    public ProductRecommendedDTO getRecommendation(long customerId) {
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
        ProductRecommendedDTO productRecommendedDTO = productRecommended != null
                ? modelMapper.map(productRecommended, ProductRecommendedDTO.class) : null;
        log.info("RecommendationServiceImpl - Found getRecommendationDTO: "+productRecommendedDTO);
        return productRecommendedDTO;
    }

}
