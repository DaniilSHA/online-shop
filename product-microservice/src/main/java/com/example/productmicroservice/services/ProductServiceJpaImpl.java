package com.example.productmicroservice.services;

import com.example.productmicroservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceJpaImpl implements ProductService{

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceJpaImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


}
