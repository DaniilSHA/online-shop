package com.example.productmicroservice.repositories;

import com.example.productmicroservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
