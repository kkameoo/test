package com.example.ch16.service;

import com.example.ch16.dto.ProductResponseDto;
import com.example.ch16.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    //모든리스트
    List<UserResponseDto> allUser();
    //orderbyName 리스트
    List<UserResponseDto> listOrderByName();
}
