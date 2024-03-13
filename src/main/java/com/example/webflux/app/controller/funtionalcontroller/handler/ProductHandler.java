package com.example.webflux.app.controller.funtionalcontroller.handler;

import com.example.webflux.app.common.dto.ProductRequestDto;
import com.example.webflux.app.common.dto.ProductResponseDto;
import com.example.webflux.app.service.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Slf4j
@Component
@RequiredArgsConstructor
public class ProductHandler {

    private final IProductService productService;

    public Mono<ServerResponse> getAllProducts(ServerRequest serverRequest) {
        Flux<ProductResponseDto> productResponseDtoFlux = productService.getAll();

        return ServerResponse.ok().body(productResponseDtoFlux, ProductResponseDto.class);
    }

    public Mono<ServerResponse> getProductById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        Mono<ProductResponseDto> productResponseDtoMono = productService.getById(id);

        return ServerResponse.ok().body(productResponseDtoMono, ProductResponseDto.class);
    }

    public Mono<ServerResponse> getProductByName(ServerRequest serverRequest) {
        String productName = serverRequest.queryParam("product-name").orElse(null);

        Mono<ProductResponseDto> productResponseDtoMono = productService.getByName(productName);

        return ServerResponse.ok().body(productResponseDtoMono, ProductResponseDto.class);
    }

    public Mono<ServerResponse> getProductByRangeQuery(ServerRequest serverRequest) {
        Double price1 = Double.valueOf(serverRequest.queryParam("price1").orElseThrow());
        Double price2 = Double.valueOf(serverRequest.queryParam("price2").orElseThrow());

        Flux<ProductResponseDto> productResponseDtoFlux = productService.getByRangeQuery(price1, price2);

        return ServerResponse.ok().body(productResponseDtoFlux, ProductResponseDto.class);
    }

    public Mono<ServerResponse> getProductByRangeMethodQuery(ServerRequest serverRequest) {
        Double price1 = Double.valueOf(serverRequest.queryParam("price1").orElseThrow());
        Double price2 = Double.valueOf(serverRequest.queryParam("price2").orElseThrow());

        Flux<ProductResponseDto> productResponseDtoFlux = productService.getByRangeMethodQuery(price1, price2);

        return ServerResponse.ok().body(productResponseDtoFlux, ProductResponseDto.class);
    }

    public Mono<ServerResponse> saveProduct(ServerRequest serverRequest) {
        Mono<ProductResponseDto> productResponseDtoMono = serverRequest.bodyToMono(ProductRequestDto.class)
                .flatMap(productService::saveProduct);

        return ServerResponse.ok().body(productResponseDtoMono, ProductResponseDto.class);
    }

    public Mono<ServerResponse> deleteProductById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");

        Mono<ProductResponseDto> response = productService.deleteById(id);

        return ServerResponse.ok().body(response, ProductResponseDto.class);
    }
}
