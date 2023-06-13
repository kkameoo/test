package com.example.ch16.dao;

import com.example.ch16.entity.Product;

import java.util.List;

public interface ProductDAO {

    //입력
    Product insertProduct(Product product);
    //수정
    Product updateProduct(Long number, String name, int price, int stock) throws Exception;
    //삭제
    void deleteProduct(Long number) throws Exception;
    //모든 리스트
    List<Product> allProduct();
    //oderbyprice 리스트
    List<Product> listOrderByPrice();
    //byname 리스트
    List<Product> listProductByName(String name);
    //상품 정보
    Product selectProduct(Long number);

    
}
