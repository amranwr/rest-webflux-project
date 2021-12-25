package com.webflux.microservice.bootsrtrap;

import com.webflux.microservice.domain.Category;
import com.webflux.microservice.domain.Vendor;
import com.webflux.microservice.repositories.CategoryRepo;
import com.webflux.microservice.repositories.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private VendorRepo vendorRepo;
    private CategoryRepo categoryRepo;

    public DataLoader(VendorRepo vendorRepo, CategoryRepo categoryRepo) {
        this.vendorRepo = vendorRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if(vendorRepo.count().block() ==0||categoryRepo.count().block() ==0){
            vendorRepo.save(Vendor.builder().firstName("amr").lastName("anwr").build()).block();
            vendorRepo.save(Vendor.builder().firstName("rana").lastName("anwr").build()).block();
            vendorRepo.save(Vendor.builder().firstName("sama").lastName("anwr").build()).block();
            vendorRepo.save(Vendor.builder().firstName("menna").lastName("anwr").build()).block();
            categoryRepo.save(Category.builder().description("fruits").build()).block();
            categoryRepo.save(Category.builder().description("vegetables").build()).block();
            categoryRepo.save(Category.builder().description("meat").build()).block();
            categoryRepo.save(Category.builder().description("fish").build()).block();
        }


    }
}
