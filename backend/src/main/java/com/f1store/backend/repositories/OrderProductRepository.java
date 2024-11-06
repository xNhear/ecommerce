package com.f1store.backend.repositories;

import com.f1store.backend.entities.OrderProduct;
import com.f1store.backend.entities.OrderProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductKey> {
}
