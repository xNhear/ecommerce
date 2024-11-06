package com.f1store.backend.entities;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class OrderProductKey implements Serializable {
    private int orderId;
    private int productId;


    public OrderProductKey() {}

    public OrderProductKey(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

}
