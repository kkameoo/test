package com.example.ch16.repository;

import com.example.ch16.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //orderbycreate 리스트
    List<Board> findAllByOrderByCreatedAtDesc();
    //byuserid 리스트
    List<Board> findByUserId(String userId);
}
