package com.example.webflux.app.service.interfaces;

import com.example.webflux.app.common.dto.ProductRequestDto;
import com.example.webflux.app.common.dto.ProductResponseDto;
import com.example.webflux.app.model.document.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    Flux<ProductResponseDto> getAll();

    Mono<ProductResponseDto> saveProduct(ProductRequestDto productRequestDto);

    Mono<ProductResponseDto> getById(String id);

    Mono<ProductResponseDto> getByName(String name);

    Flux<ProductResponseDto> getByRangeQuery(Double price1, Double price2);

    Flux<ProductResponseDto> getByRangeMethodQuery(Double price1, Double price2);

    Mono<ProductResponseDto> deleteById(String id);
}
