package com.example.webflux.app.controller.funtionalcontroller.config;

import com.example.webflux.app.controller.funtionalcontroller.handler.CategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
@RequiredArgsConstructor
public class CategoryRouterConfig {

    private final CategoryHandler categoryHandler;

    @Bean
    public RouterFunction<ServerResponse> categoryRouterFunction() {
        return RouterFunctions.route()
                .GET("/router-controller/category", categoryHandler::getAllCategories)
                .GET("/router-controller/category/name", categoryHandler::getCategoryByName)
                .GET("/router-controller/category/{id}", categoryHandler::getCategoryById)
                .POST("/router-controller/category", categoryHandler::saveCategory)
                .PUT("/router-controller/category", categoryHandler::updateCategory)
                .DELETE("/router-controller/category/{id}", categoryHandler::deleteCategoryById)
                .build();

    }

}
