package com.example.webflux.app.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponseDto {
    private String id;
    private String name;
    private Double price;
    private Integer quantity;
    private LocalDate createAt;
    private CategoryDto category;
    private Double total;
}
