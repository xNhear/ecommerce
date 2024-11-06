package com.f1store.backend.services;

import com.f1store.backend.dto.OrderDto;
import com.f1store.backend.dto.OrderProductDto;
import com.f1store.backend.entities.*;
import com.f1store.backend.repositories.OrderProductRepository;
import com.f1store.backend.repositories.OrderRepository;
import com.f1store.backend.repositories.ProductRepository;
import com.f1store.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;
        @Transactional
        public Order createOrder(OrderDto orderdto) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user1 = userRepository.findByUsername(username);
            Order ordine = new Order();
            ordine.setUser(user1);
            ordine.setOrderDate(new Date());
            orderRepository.save(ordine);
            if(orderdto.getOrderProduct().isEmpty()){
                throw new RuntimeException("Nessun prodotto selezionato");
            }
            for (OrderProductDto orderProduct : orderdto.getOrderProduct()) {
                Product product = productRepository.findById(orderProduct.getProduct().getId()).orElseThrow(() -> new RuntimeException("Product not found"));
                if (product.getQuantity() < orderProduct.getQuantity()) {
                    throw new RuntimeException("Insufficient quantity for product: " + product.getName());
                }

                product.setQuantity(product.getQuantity() - orderProduct.getQuantity());
                productRepository.save(product);
                OrderProduct orderProduct1 = new OrderProduct();
                orderProduct1.setId(new OrderProductKey(ordine.getId(),product.getId()));
                orderProduct1.setOrder(ordine);
                orderProduct1.setProduct(product);
                orderProduct1.setQuantity(orderProduct.getQuantity());
                orderProductRepository.save(orderProduct1);
            }

            return ordine;
        }

    public List<Order> getOrdersByUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findByUser(user);
    }

}
