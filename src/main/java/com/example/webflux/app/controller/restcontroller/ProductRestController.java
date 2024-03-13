package com.example.webflux.app.controller.restcontroller;

import com.example.webflux.app.common.dto.ProductRequestDto;
import com.example.webflux.app.common.dto.ProductResponseDto;
import com.example.webflux.app.service.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest-controller/product")
public class ProductRestController {
    private final IProductService productService;

    @GetMapping
    public Flux<ProductResponseDto> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ProductResponseDto> getProductById(@PathVariable("id") String id) {
        return productService.getById(id);
    }

    @GetMapping("/name")
    public Mono<ProductResponseDto> getProductByName(@RequestParam(name = "product-name") String productName) {
        return productService.getByName(productName);
    }

    @GetMapping("/range-query")
    public Flux<ProductResponseDto> getProductByRangeQuery(@RequestParam(name = "price1") Double price1, @RequestParam(name = "price2") Double price2) {
        return productService.getByRangeQuery(price1, price2);
    }

    @GetMapping("/range-method-query")
    public Flux<ProductResponseDto> getProductByRangeMethodQuery(@RequestParam(name = "price1") Double price1, @RequestParam(name = "price2") Double price2) {
        return productService.getByRangeMethodQuery(price1, price2);
    }

    @PostMapping
    public Mono<ProductResponseDto> saveProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productService.saveProduct(productRequestDto);
    }

    @DeleteMapping("/{id}")
    public Mono<ProductResponseDto> deleteProductById(@PathVariable("id") String id) {
        return productService.deleteById(id);
    }
}
