package com.example.gateway.grpc;

import com.example.gateway.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto findProductById(long id);

    List<ProductDto> getProductsList();
}
