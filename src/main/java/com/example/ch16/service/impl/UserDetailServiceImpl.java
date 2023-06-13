package com.example.ch16.service.impl;

import com.example.ch16.repository.UserRepository;
import com.example.ch16.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //필수 매개변수 만들어주는것(public UserDetailServiceImpl.... constructor 생략가능!)
public class UserDetailServiceImpl implements UserDetailService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.getByUid(username);
    }
}
