package com.f1store.backend.repositories;

import com.f1store.backend.entities.Order;
import com.f1store.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByUserId(int userId);

    List<Order> findByUser(User user);
}
