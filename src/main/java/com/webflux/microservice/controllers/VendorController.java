package com.webflux.microservice.controllers;

import com.webflux.microservice.domain.Vendor;
import com.webflux.microservice.repositories.VendorRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/vendor/")
public class VendorController {
    private final VendorRepo vendorRepo;

    public VendorController(VendorRepo vendorRepo) {
        this.vendorRepo = vendorRepo;
    }

    @GetMapping("")
    public Flux<Vendor> getAllVendors(){
        return vendorRepo.findAll();
    }
    @GetMapping("{id}")
    public Mono<Vendor> getAllVendors(@PathVariable("id") String id){
        return vendorRepo.findById(id);
    }
}
