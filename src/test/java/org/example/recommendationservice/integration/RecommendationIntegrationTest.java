package org.example.recommendationservice.integration;

import lombok.extern.slf4j.Slf4j;
import org.example.modelproject.dto.ProductRecommendedDTO;
import org.example.recommendationservice.security.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TestSecurityConfig.class)
public class RecommendationIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Transactional
    public void testRecommendation(){
        log.info("Test - testRecommendation");
        String url = "http://localhost:"+port+"/api/v1/recommendation/1";

        HttpEntity<ProductRecommendedDTO> requestEntity = new HttpEntity<>(null,null);
        ResponseEntity<ProductRecommendedDTO> response = testRestTemplate.exchange(url,
                HttpMethod.GET, requestEntity, ProductRecommendedDTO.class);

        log.info("Response: "+response.getBody());
        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
    }

}
