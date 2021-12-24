package com.webflux.microservice.repositories;

import com.webflux.microservice.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepo extends ReactiveMongoRepository<Category,String> {
}
