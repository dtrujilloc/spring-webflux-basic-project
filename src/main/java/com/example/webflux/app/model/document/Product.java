package com.example.webflux.app.model.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

/**
 * Clase que representa un documento dentro de la coleccion de Products
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Products")
public class Product {
    @MongoId
    private String id;
    private String name;
    private Double price;
    private Integer quantity;
    private LocalDate createAt;
    private Category category;

}
