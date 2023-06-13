package com.example.ch16.repository;

import com.example.ch16.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, Long> {

    //가격순
    List<Product> findAllByOrderByPriceDesc();
    //이름으로
    List<Product> findByName(String name);
}
