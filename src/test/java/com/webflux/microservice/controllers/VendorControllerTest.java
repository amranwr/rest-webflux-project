package com.webflux.microservice.controllers;

import com.webflux.microservice.domain.Category;
import com.webflux.microservice.domain.Vendor;
import com.webflux.microservice.repositories.VendorRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class VendorControllerTest {
    @Mock
    private VendorRepo vendorRepo;

    private VendorController vendorController;
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        vendorController = new VendorController(vendorRepo);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    void getAllVendors() {
        when(vendorRepo.findAll()).thenReturn(Flux.just(new Vendor(),new Vendor()));
        webTestClient.get()
                .uri("/api/v1/vendor/")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    void testGetAllVendors() {
        when(vendorRepo.findById(anyString())).thenReturn(Mono.just(new Vendor()));
        webTestClient.get()
                .uri("/api/v1/vendor/1/")
                .exchange()
                .expectBody(Vendor.class);
    }
}