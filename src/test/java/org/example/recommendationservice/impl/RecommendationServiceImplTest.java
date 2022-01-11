package org.example.recommendationservice.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.modelproject.model.Category;
import org.example.modelproject.model.Product;
import org.example.modelproject.dto.ProductRecommendedDTO;
import org.example.recommendationservice.repository.RecommendationRepository;
import org.example.recommendationservice.service.impl.RecommendationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Slf4j
@SpringBootTest
public class RecommendationServiceImplTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private RecommendationRepository recommendationRepository;

    @InjectMocks
    private RecommendationServiceImpl recommendationService;

    private List<Product> listProducts;
    private ProductRecommendedDTO product;
    private Category category;

    @BeforeEach
    public void setUp() throws Exception{
        listProducts = new ObjectMapper().readValue(
                new File("src/test/resource/ListProducts.json"),
                new TypeReference<List<Product>>() {
                });

        product = new ObjectMapper().readValue(
                new File("src/test/resource/Product.json"),
                new TypeReference<ProductRecommendedDTO>() {
                });
    }

    @Test
    public void testRecommendation(){
        log.info("Test - testRecommendation");

        Mockito.when(recommendationRepository.findAll()).thenReturn(listProducts);

        ProductRecommendedDTO actualListProductsDTO = recommendationService.getRecommendation(1L);
        assertNotEquals(product, null);
    }

}
