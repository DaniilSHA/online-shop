package com.example.gateway.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.gateway.dto.Product;
import com.example.gateway.grpc.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductsResolver implements GraphQLQueryResolver {

    private ProductService productService;

    @Autowired
    public ProductsResolver(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> products() {

        List<Product> list = new ArrayList<>();
        Collections.addAll(
                list,
                new Product(1L, "a", "a", "b", "s"),
                new Product(2L, "a", "a", "b", "s"));

        return list;
    }

    public Product product(long id) {
        return new Product(1L, "a", "a", "b", "s");
    }
}
