package com.example.productmicroservice.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Service
public class GrpcServer {

    private ProductServiceGrpcImpl productServiceGrpc;

    @Value("${grpc.product.server.port}")
    private String grpcProductServerPort;
    private Server server;

    @Autowired
    public GrpcServer(ProductServiceGrpcImpl productServiceGrpc) {
        this.productServiceGrpc = productServiceGrpc;
        server = ServerBuilder
                .forPort(8100)
                .addService(productServiceGrpc)
                .build();
    }

    @PostConstruct
    public void startUp() {
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void close() {
        server.shutdownNow();
    }
}
