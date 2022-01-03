package org.example.recommendationservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.modelproject.Product;
import org.example.recommendationservice.dto.ProductDTO;
import org.example.recommendationservice.service.impl.RecommendationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(RecommendationController.class)
public class RecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RecommendationServiceImpl recommendationService;

    private List<Product> listProducts;
    private ProductDTO product;
    private final String url = "/api/v1/recommendation/1";

    @BeforeEach
    public void setUp() throws Exception {
        listProducts = new ObjectMapper().readValue(
                new File("src/test/resource/ListProducts.json"),
                new TypeReference<List<Product>>() {
                });
        product = new ObjectMapper().readValue(
                new File("src/test/resource/Product.json"),
                new TypeReference<ProductDTO>() {
                });
    }

    @Test
    public void testRecommendation() throws Exception{
        log.info("Test - testRecommendation");
        Mockito.when(recommendationService.getRecommendation(anyLong())).thenReturn(product);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        assertNotEquals(actualJsonResponse, null);
    }

}
