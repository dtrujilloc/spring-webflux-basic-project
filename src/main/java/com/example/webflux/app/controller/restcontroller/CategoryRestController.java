package com.example.webflux.app.controller.restcontroller;

import com.example.webflux.app.common.dto.CategoryDto;
import com.example.webflux.app.common.dto.CategoryResponseDto;
import com.example.webflux.app.service.interfaces.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest-controller/category")
public class CategoryRestController {
    private final ICategoryService categoryService;

    @GetMapping
    public Flux<CategoryResponseDto> getAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<CategoryResponseDto> getCategoryById(@PathVariable("id") String id) {
        return categoryService.getById(id);
    }

    @GetMapping("/name")
    public Mono<CategoryResponseDto> getCategoryByName(@RequestParam(name = "category-name") String categoryName) {
        return categoryService.getByName(categoryName);
    }

    @PostMapping
    public Mono<CategoryResponseDto> saveCategory(@RequestParam(name = "category-name") String categoryName) {
        return categoryService.save(categoryName);
    }

    @PutMapping
    public Mono<CategoryResponseDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.update(categoryDto);
    }

    @DeleteMapping("/{id}")
    public Mono<CategoryResponseDto> deleteCategoryById(@PathVariable("id") String id) {
        return categoryService.deleteById(id);
    }
}
