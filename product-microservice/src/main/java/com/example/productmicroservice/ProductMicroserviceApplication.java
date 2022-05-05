package com.example.productmicroservice;

import com.example.productmicroservice.grpc.GrpcServer;
import com.example.productmicroservice.grpc.ProductServiceGrpcImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class ProductMicroserviceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ProductMicroserviceApplication.class, args);
    }
}
