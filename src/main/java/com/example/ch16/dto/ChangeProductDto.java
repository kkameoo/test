package com.example.ch16.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class ChangeProductDto {

    private Long number;
    private String name;
    private int price;
    private int stock;
}
