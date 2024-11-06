package com.f1store.backend.dto;

import com.f1store.backend.entities.OrderProduct;
import com.f1store.backend.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Set<OrderProductDto> orderProduct = new HashSet<>();

}
