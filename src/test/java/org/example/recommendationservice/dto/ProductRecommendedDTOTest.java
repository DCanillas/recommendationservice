package org.example.recommendationservice.dto;

import lombok.extern.slf4j.Slf4j;
import org.example.modelproject.dto.ProductRecommendedDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
@SpringBootTest(classes = ProductRecommendedDTO.class)
public class ProductRecommendedDTOTest {

    @Test
    public void testConstructor(){
        log.info("Test - testConstructor");
        ProductRecommendedDTO product = new ProductRecommendedDTO();
        assertThat(product).isNotNull();
    }

    @Test
    public void testMethodsData(){
        log.info("Test - testMethodsData");
        ProductRecommendedDTO product = new ProductRecommendedDTO();
        product.setId(1);
        product.setName("Sagrada");
        product.setDescription("Build your own church");
        product.setPrice(35.95);
        log.info("Product: "+product);
        assertThat(product.hashCode()).isNotEqualTo(new ProductRecommendedDTO().hashCode());

        ProductRecommendedDTO product2 = new ProductRecommendedDTO();
        product2.setId(product.getId());
        product2.setName(product.getName());
        product2.setDescription(product.getDescription());
        product2.setPrice(product.getPrice());
        assertTrue(product.equals(product2));
    }

}
