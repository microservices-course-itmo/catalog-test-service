package com.wine.to.up.test.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CatalogTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogTestApplication.class, args);
    }

}
