package com.example.webflux.app.model.repository;

import com.example.webflux.app.model.document.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * Interface que permite la conexion e interaccion con la BD relacionada a la coleccion de Category
 */
public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
    Mono<Category> findByName(String name);
}
