package org.example.recommendationservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.modelproject.dto.ProductRecommendedDTO;
import org.example.modelproject.model.Product;
import org.example.recommendationservice.security.TestSecurityConfig;
import org.example.recommendationservice.service.impl.RecommendationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyLong;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TestSecurityConfig.class)
public class RecommendationControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RecommendationServiceImpl recommendationService;

    private RecommendationController recommendationController;

    private List<Product> listProducts;
    private ProductRecommendedDTO product;

    @BeforeEach
    public void setUp() throws Exception {
        recommendationController = new RecommendationController(recommendationService);
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
    public void testRecommendation() throws Exception{
        log.info("Test - testRecommendation");
        Mockito.when(recommendationService.getRecommendation(anyLong())).thenReturn(product);

        ResponseEntity<ProductRecommendedDTO> response = recommendationController.getRecommendation(Long.valueOf(12));

        String actualJsonResponse = objectMapper.writeValueAsString(response.getBody());
        assertNotEquals(actualJsonResponse, null);
    }

}
