package com.example.gateway.grpc;

import com.example.gateway.dto.ProductDto;

public interface ProductService {

    ProductDto findProductById(long id);

}
