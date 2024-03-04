package com.example.webflux.app.service.mapper;

import com.example.webflux.app.common.dto.CategoryDto;
import com.example.webflux.app.common.dto.ProductRequestDto;
import com.example.webflux.app.common.dto.ProductResponseDto;
import com.example.webflux.app.model.document.Category;
import com.example.webflux.app.model.document.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class ProductMapper {

    public ProductResponseDto mapProductToProductResponseDto(Product product) {
        log.info("> Start mapProductToProductResponseDto");
        // calcula el total
        Double total = product.getPrice() * product.getQuantity();

        // crea el objeto de CategoryDTO con la info de Category
        CategoryDto categoryDto = CategoryDto.builder()
                .id(product.getId())
                .name(product.getName())
                .build();

        // crea el objeto de ProductResponseDto con la info de Product y la info anterior
        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .createAt(product.getCreateAt())
                .category(categoryDto)
                .total(total)
                .build();

        log.info("< End mapProductToProductResponseDto");
        return productResponseDto;
    }

    public Product mapProductRequestDtoToProduct(ProductRequestDto productRequestDto) {
        log.info("> Start mapProductRequestDtoToProduct");

        // Crea el objeto Category apartir del objeto interno de CategoryDto
        Category category = Category.builder()
                .id(productRequestDto.getCategory().getId())
                .name(productRequestDto.getCategory().getName())
                .build();

        // Crea el objeto de Product a partir de la info de ProductRequestDto y la info anterior
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .category(category)
                .createAt(LocalDate.now())
                .build();

        log.info("< End mapProductRequestDtoToProduct");
        return product;
    }

}
