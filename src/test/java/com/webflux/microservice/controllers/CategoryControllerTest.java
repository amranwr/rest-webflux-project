package com.webflux.microservice.controllers;

import com.webflux.microservice.domain.Category;
import com.webflux.microservice.repositories.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {
    private WebTestClient webTestClient;
    private CategoryController categoryController;
    @Mock
    private CategoryRepo categoryRepo;

    @BeforeEach
    void setUp() {
        categoryController = new CategoryController(categoryRepo);
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    void getAllCategories() {
        when(categoryRepo.findAll()).thenReturn(Flux.just(Category.builder().description("amr").build(),
                Category.builder().description("amr").build()));
        /*BDDMockito.given(categoryRepo.findAll())
                .willReturn(Flux.just(Category.builder().description("Cat1").build(),
                        Category.builder().description("Cat2").build()));*/
        webTestClient.get()
                .uri("/api/v1/category/")
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    void getCategory() {
        when(categoryRepo.findById(anyString())).thenReturn(Mono.just(Category.builder().description("Cat2").build()));
        webTestClient.get()
                .uri("/api/v1/category/1")
                .exchange()
                .expectBody(Category.class);
    }

    @Test
    void createCategory(){
        when(categoryRepo.saveAll(any(Publisher.class))).thenReturn(Flux.just(new Category()));
        Mono<Category> mono = Mono.just(new Category());
        webTestClient.post()
                .uri("/api/v1/category/")
                .body(mono,Category.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void updateCategory(){
        when(categoryRepo.save(any(Category.class))).thenReturn(Mono.just(new Category()));
        when(categoryRepo.findById(anyString())).thenReturn(Mono.just(new Category()));
        Mono<Category> mono = Mono.just(Category.builder().build());
        webTestClient.put()
                .uri("/api/v1/category/1/")
                .body(mono,Category.class)
                .exchange()
                .expectStatus()
                .isOk();
    }
}