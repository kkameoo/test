package com.example.ch16.repository;


import com.example.ch16.entity.Product;
import com.example.ch16.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByUid(String uid);

    List<User> findAllByOrderByName();

}
