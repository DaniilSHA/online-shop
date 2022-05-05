package com.example.productmicroservice.grpc;

import com.example.grpc.ProductServiceOuterClass;
import com.example.productmicroservice.domain.Category;
import com.example.productmicroservice.domain.Product;
import com.example.productmicroservice.services.ProductService;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class ProductServiceGrpcImpl extends com.example.grpc.ProductServiceGrpc.ProductServiceImplBase {

    private ProductService productService;

    @Autowired
    public ProductServiceGrpcImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void getProductList(ProductServiceOuterClass.GetProductListRequest request, StreamObserver<ProductServiceOuterClass.GetProductListResponse> responseObserver) {
        super.getProductList(request, responseObserver);
    }

    @Override
    public void getProductById(ProductServiceOuterClass.GetProductByIdRequest request, StreamObserver<ProductServiceOuterClass.GetProductByIdResponse> responseObserver) {

        Product productById = productService.getById(request.getId());

        ProductServiceOuterClass.GetProductByIdResponse response = ProductServiceOuterClass.GetProductByIdResponse
                .newBuilder()
                .setId(productById.getId() == null ? 0 : productById.getId())
                .setPrice(productById.getPrice() == null ? 0 : productById.getPrice().longValue())
                .setTitle(productById.getTitle() == null ? "" : productById.getTitle())
                .setImg(ByteString.copyFrom(productById.getImg() == null ? new byte[] {} : productById.getImg()))
                .addAllCategories(productById.getCategories() == null ?
                       Collections.emptyList() : productById.getCategories().stream().map(Category::getId).collect(Collectors.toList()))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteProductById(ProductServiceOuterClass.DeleteProductByIdRequest request, StreamObserver<ProductServiceOuterClass.DeleteProductByIdResponse> responseObserver) {
        super.deleteProductById(request, responseObserver);
    }

    @Override
    public void addProduct(ProductServiceOuterClass.AddProductRequest request, StreamObserver<ProductServiceOuterClass.AddProductResponse> responseObserver) {
        super.addProduct(request, responseObserver);
    }

    @Override
    public void updateProduct(ProductServiceOuterClass.UpdateProductRequest request, StreamObserver<ProductServiceOuterClass.UpdateProductResponse> responseObserver) {
        super.updateProduct(request, responseObserver);
    }
}
