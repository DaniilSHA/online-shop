package com.example.productmicroservice.services;

import com.example.productmicroservice.domain.Product;

import java.util.List;

public interface ProductService {
    Product getById(long id);
    List<Product> getProductsList();
}
