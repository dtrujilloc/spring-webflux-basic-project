package com.example.webflux.app.service.impl;

import com.example.webflux.app.common.dto.ProductRequestDto;
import com.example.webflux.app.common.dto.ProductResponseDto;
import com.example.webflux.app.model.document.Product;
import com.example.webflux.app.model.repository.ProductRepository;
import com.example.webflux.app.service.interfaces.IProductService;
import com.example.webflux.app.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Flux<ProductResponseDto> getAll() {
        log.info("> Start getAll");
        Flux<ProductResponseDto> productResponseDtoFlux = productRepository.findAll()
                .map(product -> {

                    product.setName(product.getName().toUpperCase());

                    return product;
                })
                .map(product -> productMapper.mapProductToProductResponseDto(product));
        log.info("< End getAll");
        return productResponseDtoFlux;
    }

    @Override
    public Mono<ProductResponseDto> saveProduct(ProductRequestDto productRequestDto) {
        log.info("> Start saveProduct");

        Product productToSave = productMapper.mapProductRequestDtoToProduct(productRequestDto);

        Mono<Product> productMonoSaved = productRepository.save(productToSave);

        Mono<ProductResponseDto> productMonoResponseDto = productMonoSaved
                .map(productMapper::mapProductToProductResponseDto);

        log.info("< End saveProduct");
        return productMonoResponseDto;
    }

    @Override
    public Mono<ProductResponseDto> getById(String id) {
        log.info("> Start getById -> id:{}", id);

        Mono<ProductResponseDto> productResponseDtoMono = productRepository.findById(id)
                .map(productMapper::mapProductToProductResponseDto);

        log.info("< End getById");
        return productResponseDtoMono;
    }

    @Override
    public Mono<ProductResponseDto> getByName(String name) {
        log.info("> Start getByName -> name:{}", name);

        Mono<ProductResponseDto> productResponseDtoMono = productRepository.findByName(name)
                .map(productMapper::mapProductToProductResponseDto);

        log.info("< End getByName");
        return productResponseDtoMono;
    }

    @Override
    public Flux<ProductResponseDto> getByRangeQuery(Double price1, Double price2) {
        log.info("> Start getByRangeQuery -> price1:{} - price2:{}", price1, price2);

        Flux<ProductResponseDto> productResponseDtoFlux = productRepository.findByPriceBetween(price1, price2)
                .map(productMapper::mapProductToProductResponseDto);

        log.info("< end getByRangeQuery");
        return productResponseDtoFlux;
    }

    @Override
    public Flux<ProductResponseDto> getByRangeMethodQuery(Double price1, Double price2) {
        log.info("> Start getByRangeMethodQuery -> price1:{} - price2:{}", price1, price2);

        Flux<ProductResponseDto> productResponseDtoFlux = productRepository.findByPriceRangeQuery(price1, price2)
                .map(productMapper::mapProductToProductResponseDto);

        log.info("< end getByRangeMethodQuery");
        return productResponseDtoFlux;
    }

    @Override
    public Mono<Void> deleteById(String id) {
        log.info("> Start deleteById -> id:{}", id);

        Mono<Void> response = productRepository.deleteById(id);

        log.info("< End deleteById");
        return response;
    }
}
