package com.example.ch16.dao.impl;

import com.example.ch16.dao.OrderDAO;
import com.example.ch16.entity.Order;
import com.example.ch16.entity.Product;
import com.example.ch16.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAOImpl implements OrderDAO {
    private final OrderRepository orderRepository;

    public OrderDAOImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //입력
    @Override
    public Order insertOrder(Order order) {
        Order saveOrder = orderRepository.save(order);
        return saveOrder;
    }
    //모든 리스트
    @Override
    public List<Order> allOrder() {
        List<Order> allOrder = orderRepository.findAll();
        return allOrder;
    }
    //byuserid 리스트
    @Override
    public List<Order> listOrderByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
    //byproductid 리스트
    @Override
    public List<Order> listOrderByProductId(long productId) {
        return orderRepository.findByProductId(productId);
    }
    //주문 정보
    @Override
    public Order selectOrder(Long id) {
        Order selectOrder = orderRepository.getReferenceById(id);
        return selectOrder;
    }


}
