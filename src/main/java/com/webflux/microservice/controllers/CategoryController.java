package com.webflux.microservice.controllers;

import com.webflux.microservice.domain.Category;
import com.webflux.microservice.repositories.CategoryRepo;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Mono<Void> create(@RequestBody Publisher<Category> categoryPublisher){
        return categoryRepo.saveAll(categoryPublisher).then();
    }

    @PutMapping("{id}")
    public Mono<Category> update(@RequestBody Category category , @PathVariable String id){
           return categoryRepo.findById(id)
                   .flatMap(foundCategory ->{
                       category.setId(foundCategory.getId());
                       return categoryRepo.save(category);
                   })
                   .switchIfEmpty(Mono.error(new Exception("category not found")));
    }
}
