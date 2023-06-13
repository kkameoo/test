package com.example.ch16.dao;

import com.example.ch16.entity.Board;
import com.example.ch16.entity.Product;

import java.util.List;

public interface BoardDAO {
    //입력
    Board insertBoard(Board board);
    //수정
    Board updateBoard(Long id,String title, String contents) throws Exception;
    //삭제
    void deleteBoard(Long id) throws Exception;
    //게시판 리스트
    List<Board> allBoard();
    //orderbycreate 리스트
    List<Board> listOrderByCreatedAt();
    //byuserid 리스트
    List<Board> listBoardByUserId(String userId);
    //게시판 정보
    Board selectBoard(Long id);

}
