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
import java.util.List;
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

        List<Product> productList = productService.getProductsList();

        ProductServiceOuterClass.GetProductListResponse response = ProductServiceOuterClass.GetProductListResponse
                .newBuilder()
                .addAllProductList(productList.stream().map(this::fromProduct).collect(Collectors.toList()))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getProductById(ProductServiceOuterClass.GetProductByIdRequest request, StreamObserver<ProductServiceOuterClass.GetProductByIdResponse> responseObserver) {

        Product productById = productService.getById(request.getId());

        ProductServiceOuterClass.GetProductByIdResponse response = fromProduct(productById);

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

    private ProductServiceOuterClass.GetProductByIdResponse fromProduct(Product product) {
        return ProductServiceOuterClass.GetProductByIdResponse
                    .newBuilder()
                    .setId(product.getId() == null ? 0 : product.getId())
                    .setPrice(product.getPrice() == null ? 0 : product.getPrice().longValue())
                    .setTitle(product.getTitle() == null ? "" : product.getTitle())
                    .setImg(ByteString.copyFrom(product.getImg() == null ? new byte[]{} : product.getImg()))
                    .addAllCategories(product.getCategories() == null ?
                            Collections.emptyList() : product.getCategories().stream().map(Category::getId).collect(Collectors.toList()))
                    .build();
    }
}
