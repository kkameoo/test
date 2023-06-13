package com.example.ch16.controller;

import com.example.ch16.dto.ProductResponseDto;
import com.example.ch16.dto.UserResponseDto;
import com.example.ch16.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserResponseDto>> allUser() {
        List<UserResponseDto> userResponseDto = userService.allUser();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    //orderbyname 리스트
    @GetMapping("/listOrderByName")
    public ResponseEntity<List<UserResponseDto>> listOrderByName() {
        List<UserResponseDto> userResponseDto = userService.listOrderByName();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

//    @PostMapping()
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public String getUserTest() {
//        return "Get User Test";
//    }
//    @PostMapping("/admin")
//    public String adminTest() {
//        return "Admin Test";
//    }
}
