package com.example.gateway.grpc;

import com.example.gateway.dto.ProductDto;
import com.example.grpc.ProductServiceGrpc;
import com.example.grpc.ProductServiceOuterClass;
import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceGrpcImpl implements ProductService {

    private ManagedChannel managedChannel;
    private ProductServiceGrpc.ProductServiceBlockingStub stub;

    @Autowired
    public ProductServiceGrpcImpl(ManagedChannel managedChannel) {
        this.managedChannel = managedChannel;
        this.stub = ProductServiceGrpc.newBlockingStub(this.managedChannel);
    }

    public ProductDto findProductById(long id) {

        ProductServiceOuterClass.GetProductByIdRequest request = ProductServiceOuterClass.GetProductByIdRequest
                .newBuilder()
                .setId(id)
                .build();

        ProductServiceOuterClass.GetProductByIdResponse productByIdGrpcResponse = stub.getProductById(request);

        return new ProductDto(
                productByIdGrpcResponse.getId(),
                productByIdGrpcResponse.getTitle(),
                BigDecimal.valueOf(productByIdGrpcResponse.getPrice()),
                productByIdGrpcResponse.getImg().toByteArray(),
                productByIdGrpcResponse.getCategoriesList()
        );
    }
}
