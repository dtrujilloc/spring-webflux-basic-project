package com.example.webflux.app.model.repository;

import com.example.webflux.app.model.document.Category;
import com.example.webflux.app.model.document.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interface que permite la conexion e interaccion con la BD relacionada a la coleccion de Product
 */
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    /**
     * Query que me permite encontrar una producto por su nombre
     * @param name String que representa el nombre del producto que quiero buscar
     * @return Un Mono de Producto
     */
    Mono<Product> findByName(String name);

    /**
     * Query que me permite buscar los productos que esten en un rango de precio. La query se especifica en la etiqueta
     * @param price1 Double que representa el valor minimo del rango
     * @param price2 Double que representa el valor maximo del rango
     * @return un Flux de Productos
     */
    @Query("{price:{$gte: ?0 , $lt: ?1}}")
    Flux<Product> findByPriceRangeQuery(Double price1, Double price2);

    /**
     * Query que me permite buscar los productos que esten en un rango de precio. La query se construye a partir de la
     * anotacion del metodo
     * @param price1 Double que representa el valor minimo del rango
     * @param price2 Double que representa el valor maximo del rango
     * @return un Flux de Productos
     */
    Flux<Product> findByPriceBetween(Double price1, Double price2);


}
