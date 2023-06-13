package com.example.ch16.service;

import com.example.ch16.dto.*;

import java.util.List;

public interface OrderService {
    //입력
    OrderResponseDto saveOrder(Long productId);
    //모든리스트
    List<OrderResponseDto> allOrder();
    //byuserid 리스트
    List<OrderResponseDto> listByUserId(String userId);
    //byproductid 리스트
    List<OrderResponseDto> listByProductId(long productId);
    //주문 정보
    OrderResponseDto getOrder(Long id);
}
