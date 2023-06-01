package com.johara.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableKafka
public class ProductServiceApplication {

	public static void main(String[] args) {

		System.setProperty("spring.config.name", "product-service");
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
