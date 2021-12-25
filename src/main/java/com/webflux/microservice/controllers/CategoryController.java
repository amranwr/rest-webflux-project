package com.webflux.microservice.controllers;

import com.webflux.microservice.domain.Category;
import com.webflux.microservice.repositories.CategoryRepo;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryRepo categoryRepo;

    public CategoryController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("")
    public Flux<Category> getAllCategories(){
        return categoryRepo.findAll();
    }

    @GetMapping("{id}")
    public Mono<Category> getCategory(@PathVariable("id") String id ){
        return categoryRepo.findById(id);
    }
}
