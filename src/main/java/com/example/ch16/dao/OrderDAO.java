package com.example.ch16.dao;

import com.example.ch16.entity.Board;
import com.example.ch16.entity.Order;
import com.example.ch16.entity.Product;

import java.util.List;

public interface OrderDAO {
    //입력
    Order insertOrder(Order order);
    //주문 리스트
    List<Order> allOrder();
    //byuserid 리스트
    List<Order> listOrderByUserId(String userId);
    //byproductid 리스트
    List<Order> listOrderByProductId(long productId);
    //주문 정보
    Order selectOrder(Long id);
}
