package com.example.webflux.app.controller.funtionalcontroller.handler;

import com.example.webflux.app.common.dto.CategoryDto;
import com.example.webflux.app.common.dto.CategoryResponseDto;
import com.example.webflux.app.service.interfaces.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CategoryHandler {

    private final ICategoryService categoryService;

    public Mono<ServerResponse> getAllCategories(ServerRequest serverRequest) {
        Flux<CategoryResponseDto> response = categoryService.getAll();

        return ServerResponse.ok().body(response, CategoryResponseDto.class);
    }

    public Mono<ServerResponse> getCategoryById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        Mono<CategoryResponseDto> response = categoryService.getById(id);

        return ServerResponse.ok().body(response, CategoryResponseDto.class);
    }

    public Mono<ServerResponse> getCategoryByName(ServerRequest serverRequest) {
        String categoryName = serverRequest.queryParam("category-name").orElseThrow();

        Mono<CategoryResponseDto> response = categoryService.getByName(categoryName);

        return ServerResponse.ok().body(response, CategoryResponseDto.class);
    }

    public Mono<ServerResponse> saveCategory(ServerRequest serverRequest) {
        String categoryName = serverRequest.queryParam("category-name").orElseThrow();

        Mono<CategoryResponseDto> response = categoryService.save(categoryName);

        return ServerResponse.ok().body(response, CategoryResponseDto.class);
    }

    public Mono<ServerResponse> updateCategory(ServerRequest serverRequest) {
        Mono<CategoryResponseDto> response = serverRequest.bodyToMono(CategoryDto.class)
                .flatMap(categoryService::update);

        return ServerResponse.ok().body(response, CategoryResponseDto.class);
    }

    public Mono<ServerResponse> deleteCategoryById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");

        Mono<CategoryResponseDto> response = categoryService.deleteById(id);

        return ServerResponse.ok().body(response, CategoryResponseDto.class);
    }
}
