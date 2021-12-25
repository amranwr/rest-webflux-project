package com.webflux.microservice.controllers;

import com.webflux.microservice.domain.Vendor;
import com.webflux.microservice.repositories.VendorRepo;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Mono<Void> createVendor(@RequestBody Publisher<Vendor> vendorPublisher){
        return vendorRepo.saveAll(vendorPublisher).then();
    }

    @PutMapping("{id}")
    public Mono<Vendor> updateVendor(@RequestBody Vendor vendor, @PathVariable String id ){
        return vendorRepo.findById(id)
                .flatMap(foundVendor->{
                    vendor.setId(foundVendor.getId());
                    return vendorRepo.save(vendor);
                })
                .switchIfEmpty(Mono.error(new Exception("vendor not found")));
    }
}
