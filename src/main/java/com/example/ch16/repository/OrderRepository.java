package com.example.ch16.repository;


import com.example.ch16.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    //byuserid
    List<Order> findByUserId(String userId);
    //byproductid
    List<Order> findByProductId(long productId);
    //재고 -1
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.stock = p.stock - 1 Where p.number = :number")
    void updateStock(long number);

}
