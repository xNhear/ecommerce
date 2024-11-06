package com.f1store.backend.services;

import com.f1store.backend.entities.Product;
import com.f1store.backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByType(String type) {
        return productRepository.findByType(type);
    }

    public List<Product> getProductsBySize(String size) {
        return productRepository.findBySize(size);
    }

    public List<Product> findByTypeAndSize(String type, String size) {
        return productRepository.findByTypeAndSize(type, size);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }
}
