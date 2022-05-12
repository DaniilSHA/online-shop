package com.example.productmicroservice.services;

import com.example.productmicroservice.domain.Product;
import com.example.productmicroservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceJpaImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceJpaImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product getById(long id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> getProductsList() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.delete(getById(id));
    }

    @Override
    public void addProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.saveAndFlush(product);
    }
}
