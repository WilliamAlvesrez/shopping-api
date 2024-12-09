package com.william.api.shopping.shopping_api.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "shops")
public class Shop {
    @Id
    private String id;
    private String userIdentifier;
    private LocalDateTime date;
    private List<Item> items;
    private Double total;
}
