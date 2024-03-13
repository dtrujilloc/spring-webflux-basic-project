package com.example.webflux.app.controller.funtionalcontroller.config;

import com.example.webflux.app.controller.funtionalcontroller.handler.ProductHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class ProductRouterConfig {

    private final ProductHandler productHandler;

    @Bean
    public RouterFunction<ServerResponse> productRouterFunction() {
        return RouterFunctions.route()
                .GET("/router-controller/product", productHandler::getAllProducts)
                .GET("/router-controller/product/name", productHandler::getProductByName)
                .GET("/router-controller/product/range-query", productHandler::getProductByRangeQuery)
                .GET("/router-controller/product/range-method-query", productHandler::getProductByRangeMethodQuery)
                .GET("/router-controller/product/{id}", productHandler::getProductById)
                .POST("/router-controller/product", productHandler::saveProduct)
                .DELETE("/router-controller/product/{id}", productHandler::deleteProductById)
                .build();

    }
}
