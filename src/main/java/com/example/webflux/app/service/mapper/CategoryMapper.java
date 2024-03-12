package com.example.webflux.app.service.mapper;

import com.example.webflux.app.common.dto.CategoryResponseDto;
import com.example.webflux.app.model.document.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CategoryMapper {

    public CategoryResponseDto categoryToCategoryResponseDto(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category buildCategory(String categoryName) {
        return Category.builder()
                .name(categoryName.toUpperCase())
                .build();
    }

}
