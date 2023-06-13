package com.example.ch16.dto;

import com.example.ch16.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private long id;
    private String uid;
    private String password;
    private String name;
    private String email;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.uid = user.getUid();
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
