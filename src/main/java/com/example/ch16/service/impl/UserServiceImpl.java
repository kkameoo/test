package com.example.ch16.service.impl;

import com.example.ch16.dao.ProductDAO;
import com.example.ch16.dao.UserDAO;
import com.example.ch16.dto.ProductResponseDto;
import com.example.ch16.dto.UserResponseDto;
import com.example.ch16.entity.Product;
import com.example.ch16.entity.User;
import com.example.ch16.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<UserResponseDto> allUser() {
        List<User> users = userDAO.allUser();
        List<UserResponseDto> userResponseDtoList = users.stream().map(user -> new UserResponseDto(user)).collect(Collectors.toList());
        return userResponseDtoList;
    }

    @Override
    public List<UserResponseDto> listOrderByName() {
        List<User> users = userDAO.listOrderByName();
        List<UserResponseDto> userResponseDtoList = users.stream().map(user -> new UserResponseDto(user)).collect(Collectors.toList());
        return userResponseDtoList;
    }
}
