package com.example.webflux.app.service.interfaces;

import com.example.webflux.app.common.dto.CategoryDto;
import com.example.webflux.app.common.dto.CategoryResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICategoryService {

    Flux<CategoryResponseDto> getAll();

    Mono<CategoryResponseDto> getById(String id);

    Mono<CategoryResponseDto> getByName(String name);

    Mono<CategoryResponseDto> save(String name);

    Mono<CategoryResponseDto> update(CategoryDto categoryDto);

    Mono<CategoryResponseDto> deleteById(String id);
}
