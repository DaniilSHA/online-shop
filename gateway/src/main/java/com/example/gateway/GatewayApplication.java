package com.example.gateway;

import com.example.gateway.dto.ProductDto;
import com.example.gateway.grpc.ProductServiceGrpcImpl;
import com.example.grpc.ProductServiceGrpc;
import com.example.grpc.ProductServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@SpringBootApplication
@Component
public class GatewayApplication {

    @Value("${grpc.product.server.address}")
    private String grpcProductServerAddress;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GatewayApplication.class, args);

        ProductServiceGrpcImpl testService = context.getBean("productServiceGrpcImpl", ProductServiceGrpcImpl.class);
        System.out.println(testService.findProductById(4L));
        System.out.println(testService.findProductById(5L));
    }

    @Bean
    public ManagedChannel managedChannel() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forTarget(grpcProductServerAddress)
                .usePlaintext()
                .build();
        return managedChannel;
    }

}
