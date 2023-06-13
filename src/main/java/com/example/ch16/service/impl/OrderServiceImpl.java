package com.example.ch16.service.impl;

import com.example.ch16.dao.OrderDAO;
import com.example.ch16.dto.OrderResponseDto;
import com.example.ch16.dto.ProductResponseDto;
import com.example.ch16.entity.Order;
import com.example.ch16.entity.User;
import com.example.ch16.repository.OrderRepository;
import com.example.ch16.service.OrderService;
import com.example.ch16.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;
    private final ProductService productService;

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO, ProductService productService, OrderRepository orderRepository) {
        this.orderDAO = orderDAO;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponseDto saveOrder(Long productId) {

        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principle;

        ProductResponseDto productResponseDto = productService.getproduct(productId);

        if (productResponseDto.getStock() <= 0) {
            return null;
        } else {
            orderRepository.updateStock(productId);

            Order order = new Order();
            order.setProductId(productId);
            order.setProductName(productResponseDto.getName());
            order.setUserId(user.getUid());
            order.setUserName(user.getName());
            order.setPrice(productResponseDto.getPrice());
            order.setCreatedAt(LocalDateTime.now());
            order.setUpdatedAt(LocalDateTime.now());

            Order saveOrder = orderDAO.insertOrder(order);

            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setId(saveOrder.getId());
            orderResponseDto.setProductId(saveOrder.getProductId());
            orderResponseDto.setProductName(saveOrder.getProductName());
            orderResponseDto.setUserId(saveOrder.getUserId());
            orderResponseDto.setUserName(saveOrder.getUserName());
            orderResponseDto.setPrice(saveOrder.getPrice());
            return orderResponseDto;
        }
    }
    //모든 리스트
    @Override
    public List<OrderResponseDto> allOrder() {
        List<Order> orders = orderDAO.allOrder();
        List<OrderResponseDto> orderResponseDtoList = orders.stream().map(order -> new OrderResponseDto(order)).collect(Collectors.toList());
        return orderResponseDtoList;
    }
    //byuserid 리스트
    @Override
    public List<OrderResponseDto> listByUserId(String userId) {
        List<Order> orders = orderDAO.listOrderByUserId(userId);
        List<OrderResponseDto> orderResponseDtoList = orders.stream().map(order -> new OrderResponseDto(order)).collect(Collectors.toList());
        return orderResponseDtoList;
    }
    //byproductid 리스트
    @Override
    public List<OrderResponseDto> listByProductId(long productId) {
        List<Order> orders = orderDAO.listOrderByProductId(productId);
        List<OrderResponseDto> orderResponseDtoList = orders.stream().map(order -> new OrderResponseDto(order)).collect(Collectors.toList());
        return orderResponseDtoList;
    }
    //주문 정보
    @Override
    public OrderResponseDto getOrder(Long id) {
        Order order = orderDAO.selectOrder(id);

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setProductId(order.getProductId());
        orderResponseDto.setProductName(order.getProductName());
        orderResponseDto.setUserId(order.getUserId());
        orderResponseDto.setUserName(order.getUserName());
        orderResponseDto.setPrice(order.getPrice());

        return orderResponseDto;
    }
}
