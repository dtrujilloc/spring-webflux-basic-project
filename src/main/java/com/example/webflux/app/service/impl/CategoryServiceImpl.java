package com.example.webflux.app.service.impl;

import com.example.webflux.app.common.dto.CategoryDto;
import com.example.webflux.app.common.dto.CategoryResponseDto;
import com.example.webflux.app.model.repository.CategoryRepository;
import com.example.webflux.app.service.interfaces.ICategoryService;
import com.example.webflux.app.service.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Flux<CategoryResponseDto> getAll() {
        return categoryRepository.findAll()
                .map(categoryMapper::categoryToCategoryResponseDto);
    }

    @Override
    public Mono<CategoryResponseDto> getById(String id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::categoryToCategoryResponseDto);
    }

    @Override
    public Mono<CategoryResponseDto> getByName(String name) {
        return categoryRepository.findByName(name)
                .map(categoryMapper::categoryToCategoryResponseDto);
    }

    @Override
    public Mono<CategoryResponseDto> save(String name) {
        return categoryRepository.save(categoryMapper.buildCategory(name))
                .map(categoryMapper::categoryToCategoryResponseDto);
    }

    @Override
    public Mono<CategoryResponseDto> update(CategoryDto categoryDto) {
        return categoryRepository.findById(categoryDto.getId())
                .flatMap(category -> {
                    if (category.getName().equalsIgnoreCase(categoryDto.getName())) {
                        return Mono.just(category);
                    }

                    category.setName(categoryDto.getName());
                    return Mono.just(category);
                })
                .flatMap(categoryRepository::save)
                .map(categoryMapper::categoryToCategoryResponseDto);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return categoryRepository.deleteById(id);
    }
}
