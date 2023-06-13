package com.example.ch16.service;

import com.example.ch16.dto.BoardDto;
import com.example.ch16.dto.BoardResponseDto;
import com.example.ch16.dto.ProductResponseDto;
import com.example.ch16.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface BoardService {
    //입력
    BoardResponseDto saveBoard(BoardDto boardDto);
    //수정
    BoardResponseDto changeBoard(Long id, String title, String contents) throws Exception;
    //삭제
    void deletBoard(Long id) throws Exception;
    //게시판 리스트
    List<BoardResponseDto> allBoard();
    //orderbycreate 리스트
    List<BoardResponseDto> listOrderByCreatedAt();
    //byuserid 리스트
    List<BoardResponseDto> listByUserId(String userId);
    //게시판 정보
    BoardResponseDto getBoard(Long id);

}
