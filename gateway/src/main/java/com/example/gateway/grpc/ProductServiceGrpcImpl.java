package com.example.gateway.grpc;

import com.example.gateway.dto.ProductDto;
import com.example.grpc.ProductServiceGrpc;
import com.example.grpc.ProductServiceOuterClass;
import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

        return toProductDto(productByIdGrpcResponse);
    }

    public List<ProductDto> getProductsList() {

        ProductServiceOuterClass.GetProductListRequest request = ProductServiceOuterClass.GetProductListRequest
                .newBuilder()
                .build();

        ProductServiceOuterClass.GetProductListResponse productListResponse = stub.getProductList(request);
        return productListResponse.getProductListList().stream().map(this::toProductDto).collect(Collectors.toList());

    }

    private ProductDto toProductDto (ProductServiceOuterClass.GetProductByIdResponse productByIdGrpcResponse){
        return new ProductDto(
                productByIdGrpcResponse.getId(),
                productByIdGrpcResponse.getTitle(),
                BigDecimal.valueOf(productByIdGrpcResponse.getPrice()),
                productByIdGrpcResponse.getImg().toByteArray(),
                productByIdGrpcResponse.getCategoriesList());
    }
}
