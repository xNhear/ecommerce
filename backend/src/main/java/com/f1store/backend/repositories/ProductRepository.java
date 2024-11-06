package com.f1store.backend.repositories;

import com.f1store.backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByType(String type);
    List<Product> findBySize(String size);
    List<Product> findByTypeAndSize(String type, String size);
}
