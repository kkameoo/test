package com.example.ch16.dao;

import com.example.ch16.entity.User;

import java.util.List;

public interface UserDAO {
    //모든 리스트
    List<User> allUser();
    //oderbyName 리스트
    List<User> listOrderByName();
}
