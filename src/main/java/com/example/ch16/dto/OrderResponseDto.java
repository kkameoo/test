package com.example.ch16.dto;

import com.example.ch16.entity.Board;
import com.example.ch16.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private long id;
    private long productId;
    private String productName;
    private String userId;
    private String userName;
    private int price;

    public OrderResponseDto(Order order) {
        this.id = order.getId();
        this.productId = order.getProductId();
        this.productName = order.getProductName();
        this.userId = order.getUserId();
        this.userName = order.getUserName();
        this.price = order.getPrice();
    }
}
