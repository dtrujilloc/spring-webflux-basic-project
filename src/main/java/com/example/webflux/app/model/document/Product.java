package com.example.webflux.app.model.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Products")
public class Product {
    @MongoId
    private String id;
    private String name;
    private Double price;
    private LocalDate createAt;
}
