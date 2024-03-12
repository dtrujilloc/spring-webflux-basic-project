package com.example.webflux.app.configuration;

import com.example.webflux.app.common.dto.ProductRequestDto;
import com.example.webflux.app.model.document.Category;
import com.example.webflux.app.model.document.Product;
import com.example.webflux.app.model.repository.CategoryRepository;
import com.example.webflux.app.model.repository.ProductRepository;
import com.example.webflux.app.service.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Clase creada con el proposito de inicializar las colecciones de la BD con los diferentes datos/documentos
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class InitDataMongoDBConfiguration implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final IProductService productService;
    private final CategoryRepository categoryRepository;

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info(">>>>> Start data initialization...");

        // Eliminar los datos existentes en las Coleciones para no repetir datos
        reactiveMongoTemplate.dropCollection("Products").subscribe();
        reactiveMongoTemplate.dropCollection("Categories").subscribe();

        // Creacion de las objeto de categoria
        Category electronicCategory = Category.builder().name("ELECTRONIC").build();
        Category sportCategory = Category.builder().name("SPORT").build();
        Category homeCategory = Category.builder().name("HOME").build();

        // Creacion de productos Dto en un list
        List<ProductRequestDto> productRequestDtoInitList = Arrays.asList(
                ProductRequestDto.builder().name("PC Asus").price(100.10).quantity(12).categoryName("ELECTRONIC").build(),
                ProductRequestDto.builder().name("TV LG").price(200.10).quantity(10).categoryName("ELECTRONIC").build(),
                ProductRequestDto.builder().name("Apple 15").price(200.10).quantity(4).categoryName("ELECTRONIC").build(),
                ProductRequestDto.builder().name("Xiaomi pro 8").price(200.10).categoryName("ELECTRONIC").quantity(7).build(),
                ProductRequestDto.builder().name("Basketball net").price(10.10).categoryName("SPORT").quantity(20).build(),
                ProductRequestDto.builder().name("Table").price(80.10).quantity(2).categoryName("HOME").build()
        );

        // Creacion del flux de categorias a partir de las categorias para ser guardadas y posteriomente la creacion
        // de un flux de productos a partir de un list de productos y guardado de cada uno de los datos
        Flux.just(electronicCategory, sportCategory, homeCategory)
                .flatMap(category -> categoryRepository.save(category))
                .doOnNext(category -> log.info("The category was saved -> name:{} - id:{}", category.getName(), category.getId()))
                .thenMany(
                        Flux.fromIterable(productRequestDtoInitList)
                                .flatMap(product -> productService.saveProduct(product))
                ).subscribe(
                        product -> log.info("The product was saved in init process -> name:{} - id:{}", product.getName(), product.getId()),
                        ex -> log.error("Error in saving process -> ex:", ex),
                        () -> log.info("<<<<< Finish initialization..."));
    }
}
