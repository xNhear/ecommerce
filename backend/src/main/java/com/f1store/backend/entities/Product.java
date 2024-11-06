package com.f1store.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private double price;
    private String type;
    private String size;
    private int quantity;
    private String imageUrl;

  //  @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL, orphanRemoval = true)
 //   private Set<OrderProduct> orderProducts = new HashSet<>();


}
