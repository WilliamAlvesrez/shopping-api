package com.william.api.shopping.shopping_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ShoppingApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShoppingApiApplication.class, args);
    }
}