package com.webflux.microservice.repositories;

import com.webflux.microservice.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepo extends ReactiveMongoRepository<Vendor,String> {
}
