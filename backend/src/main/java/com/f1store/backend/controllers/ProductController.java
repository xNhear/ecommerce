package com.f1store.backend.controllers;

import com.f1store.backend.entities.Product;
import com.f1store.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Product>> getProductsByType(@PathVariable String type) {
        return ResponseEntity.ok(productService.getProductsByType(type));
    }

    @GetMapping("/size/{size}")
    public ResponseEntity<List<Product>> getProductsBySize(@PathVariable String size) {
        return ResponseEntity.ok(productService.getProductsBySize(size));
    }

    @GetMapping("/type/{type}/size/{size}")
    public ResponseEntity<List<Product>> getProductsByTypeAndSize(@PathVariable String type, @PathVariable String size) {
        return ResponseEntity.ok(productService.findByTypeAndSize(type, size));
    }
}
