package com.example.webflux.app.model.repository;

import com.example.webflux.app.model.document.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Interface que permite la conexion e interaccion con la BD relacionada a la coleccion de Product
 */
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
