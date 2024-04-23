package com.unibank.unitech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.unibank.unitech.client")
public class UnitechApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitechApplication.class, args);
    }

}
