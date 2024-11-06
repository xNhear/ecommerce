package com.f1store.backend.controllers;

import com.f1store.backend.dto.OrderDto;
import com.f1store.backend.entities.Order;
import com.f1store.backend.entities.User;
import com.f1store.backend.services.OrderService;
import com.f1store.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/my-orders")
    public ResponseEntity<List<Order>> getMyOrders() {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = currentUser.getUsername();
        User user = userService.findByUsername(username);
        List<Order> orders = orderService.getOrdersByUser(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
