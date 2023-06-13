package com.example.ch16.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {
    private long productId;
    private String productName;
    private int price;
}
