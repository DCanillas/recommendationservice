package org.example.recommendationservice.integration;

import lombok.extern.slf4j.Slf4j;
import org.example.recommendationservice.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecommendationIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testRecommendation(){
        log.info("Test - testRecommendation");
        String url = "http://localhost:"+port+"/api/v1/recommendation/1";

        HttpEntity<ProductDTO> requestEntity = new HttpEntity<>(null,null);
        ResponseEntity<ProductDTO> response = testRestTemplate.exchange(url,
                HttpMethod.GET, requestEntity, ProductDTO.class);

        log.info("Response: "+response.getBody());
        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
    }

}
