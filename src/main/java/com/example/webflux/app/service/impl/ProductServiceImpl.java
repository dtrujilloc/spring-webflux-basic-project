package com.example.webflux.app.service.impl;

import com.example.webflux.app.common.dto.ProductRequestDto;
import com.example.webflux.app.common.dto.ProductResponseDto;
import com.example.webflux.app.model.document.Category;
import com.example.webflux.app.model.document.Product;
import com.example.webflux.app.model.repository.CategoryRepository;
import com.example.webflux.app.model.repository.ProductRepository;
import com.example.webflux.app.service.interfaces.IProductService;
import com.example.webflux.app.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Flux<ProductResponseDto> getAll() {
        return productRepository.findAll()
                .map(product -> {

                    product.setName(product.getName().toUpperCase());

                    return product;
                })
                .map(product -> productMapper.mapProductToProductResponseDto(product));
    }

    @Override
    public Mono<ProductResponseDto> saveProduct(ProductRequestDto productRequestDto) {
        return saveProductDetail(productRequestDto)
                .map(productMapper::mapProductToProductResponseDto)
                .doOnSuccess(productResponseDto -> log.info("- The product was validated, saved and mapped correctly -> productId:{} - productName:{}", productResponseDto.getId(), productResponseDto.getName()))
                .doOnError(ex -> {
                    log.error("* there is been a problem in the product save process -> exMessage:{}", ex.getMessage());
                });
    }

    private Mono<Product> saveProductDetail(ProductRequestDto productRequestDto) {
        return productRepository
                .findByName(productRequestDto.getName().toLowerCase())
                .doOnNext(product -> {
                    throw new RuntimeException("This product already exist in the DB");
                })
                .switchIfEmpty(saveProductAndBuildProductResponseDto(productRequestDto));

    }

    private Mono<Product> saveProductAndBuildProductResponseDto(ProductRequestDto productRequestDto) {
        return searchCategoryByName(productRequestDto.getCategoryName())
                .map(categoryTemp -> {
                    Product productToSaveTemp = productMapper.mapProductRequestDtoToProduct(productRequestDto);
                    productToSaveTemp.setCategory(categoryTemp);

                    return productToSaveTemp;
                })
                .flatMap(productToSaveTemp -> productRepository.save(productToSaveTemp));
    }

    private Mono<Category> searchCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName.toUpperCase())
                .switchIfEmpty(Mono.error(new RuntimeException("The category doesn't exist to relate with the product to save")));
    }

    @Override
    public Mono<ProductResponseDto> getById(String id) {
        return productRepository.findById(id)
                .map(productMapper::mapProductToProductResponseDto);
    }

    @Override
    public Mono<ProductResponseDto> getByName(String name) {
        return productRepository.findByName(name.toLowerCase())
                .map(productMapper::mapProductToProductResponseDto);
    }

    @Override
    public Flux<ProductResponseDto> getByRangeQuery(Double price1, Double price2) {
        return productRepository.findByPriceBetween(price1, price2)
                .map(productMapper::mapProductToProductResponseDto);
    }

    @Override
    public Flux<ProductResponseDto> getByRangeMethodQuery(Double price1, Double price2) {
        return productRepository.findByPriceRangeQuery(price1, price2)
                .map(productMapper::mapProductToProductResponseDto);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return productRepository.deleteById(id);
    }
}
