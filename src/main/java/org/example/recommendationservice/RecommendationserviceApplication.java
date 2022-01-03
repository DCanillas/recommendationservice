package org.example.recommendationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "org.example.modelproject")
public class RecommendationserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommendationserviceApplication.class, args);
	}

}
