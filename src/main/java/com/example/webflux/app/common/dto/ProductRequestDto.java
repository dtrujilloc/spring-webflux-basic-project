package com.example.webflux.app.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequestDto {
    private String name;
    private Double price;
    private Integer quantity;
    private CategoryDto category;

}
