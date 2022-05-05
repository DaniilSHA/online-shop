package com.example.productmicroservice.services;

import com.example.productmicroservice.domain.Product;

public interface ProductService {
    Product getById(long id);
}
