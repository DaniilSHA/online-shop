package com.example.gateway.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.gateway.dto.ProductDto;
import com.example.gateway.grpc.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductsResolver implements GraphQLQueryResolver {

    private ProductService productService;

    @Autowired
    public ProductsResolver(ProductService productService) {
        this.productService = productService;
    }

    public List<ProductDto> products() {
        return productService.getProductsList();
    }

    public ProductDto product(long id) {
        return productService.findProductById(id);
    }
}
