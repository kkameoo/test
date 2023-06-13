package com.example.ch16.dao.impl;

import com.example.ch16.dao.UserDAO;
import com.example.ch16.entity.Product;
import com.example.ch16.entity.User;
import com.example.ch16.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;

    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //모든 리스트
    @Override
    public List<User> allUser() {
        List<User> allUser = userRepository.findAll();
        return allUser;
    }

    @Override
    public List<User> listOrderByName() {
        List<User> listOrderByName = userRepository.findAllByOrderByName();
        return listOrderByName;
    }

}
