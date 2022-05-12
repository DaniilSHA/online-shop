package com.example.productmicroservice.grpc;

import com.example.grpc.ProductServiceOuterClass;
import com.example.productmicroservice.domain.Category;
import com.example.productmicroservice.domain.Product;
import com.example.productmicroservice.services.ProductService;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        ProductServiceOuterClass.GetProductByIdResponse response = null;
        try {
            Product productById = productService.getById(request.getId());
            response = fromProduct(productById);
        } catch (Exception e) {
            response = fromProduct(new Product());
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteProductById(ProductServiceOuterClass.DeleteProductByIdRequest request, StreamObserver<ProductServiceOuterClass.DeleteProductByIdResponse> responseObserver) {

        ProductServiceOuterClass.DeleteProductByIdResponse response = null;

        try {
            productService.deleteProduct(request.getId());
            response = ProductServiceOuterClass.DeleteProductByIdResponse.newBuilder().setResult(true).build();
        } catch (Exception e) {
            response = ProductServiceOuterClass.DeleteProductByIdResponse.newBuilder().setResult(false).build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void addProduct(ProductServiceOuterClass.AddProductRequest request, StreamObserver<ProductServiceOuterClass.AddProductResponse> responseObserver) {

        ProductServiceOuterClass.AddProductResponse response = null;

        try {
            productService.addProduct(toProduct(request));
            response = ProductServiceOuterClass.AddProductResponse.newBuilder().setResult(true).build();
        } catch (Exception e) {
            response = ProductServiceOuterClass.AddProductResponse.newBuilder().setResult(false).build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateProduct(ProductServiceOuterClass.UpdateProductRequest request, StreamObserver<ProductServiceOuterClass.UpdateProductResponse> responseObserver) {

        ProductServiceOuterClass.UpdateProductResponse response = null;

        try {
            productService.updateProduct(toProduct(request));
            response = ProductServiceOuterClass.UpdateProductResponse.newBuilder().setResult(true).build();
        } catch (Exception e) {
            response = ProductServiceOuterClass.UpdateProductResponse.newBuilder().setResult(false).build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();

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

    private Product toProduct (ProductServiceOuterClass.AddProductRequest request) {
        return Product.builder()
                .id(request.getId())
                .price(BigDecimal.valueOf(request.getPrice()))
                .title(request.getTitle())
                .img(request.getImg().toByteArray())
                .categories(request.getCategoriesList().stream().map(Category::new).collect(Collectors.toList()))
                .build();
    }

    private Product toProduct (ProductServiceOuterClass.UpdateProductRequest request) {
        return Product.builder()
                .id(request.getId())
                .price(BigDecimal.valueOf(request.getPrice()))
                .title(request.getTitle())
                .img(request.getImg().toByteArray())
                .categories(request.getCategoriesList().stream().map(Category::new).collect(Collectors.toList()))
                .build();
    }
}
