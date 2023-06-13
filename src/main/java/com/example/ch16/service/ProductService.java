package com.example.ch16.service;

import com.example.ch16.dto.ProductDto;
import com.example.ch16.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    //입력
    ProductResponseDto saveProduct(ProductDto productDto);
    //수정
    ProductResponseDto changeProduct(Long number, String name, int price, int stock) throws Exception;
    //삭제
    void deleteProduct(Long number) throws Exception;
    //모든리스트
    List<ProductResponseDto> allProduct();
    //orderbyprice 리스트
    List<ProductResponseDto> listOrderByPrice();
    //byname 리스트
    List<ProductResponseDto> listByName(String name);
    //상품 정보
    ProductResponseDto getproduct(Long number);
}
