package com.example.ch16.controller;

import com.example.ch16.dto.OrderResponseDto;
import com.example.ch16.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    //입력
    @PostMapping()
    public ResponseEntity<OrderResponseDto> createOrder(Long productId) {
        OrderResponseDto orderResponseDto = orderService.saveOrder(productId);
        if (orderResponseDto.equals(null)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(orderResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
        }
    }
    //모든 리스트
    @GetMapping("/list")
    public ResponseEntity<List<OrderResponseDto>> allOrder() {
        List<OrderResponseDto> orderResponseDto = orderService.allOrder();
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }
    //byuserid 리스트
    @GetMapping("/listByUserId")
    public ResponseEntity<List<OrderResponseDto>> listByUserId(String userId) {
        List<OrderResponseDto> orderResponseDto = orderService.listByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }
    //byproductid 리스트
    @GetMapping("/listByProductId")
    public ResponseEntity<List<OrderResponseDto>> listByProductId(long productId) {
        List<OrderResponseDto> orderResponseDto = orderService.listByProductId(productId);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }
    //주문 정보
    @GetMapping("/")
    public ResponseEntity<OrderResponseDto> getOrder(Long id) {
        OrderResponseDto orderResponseDto = orderService.getOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }

}
