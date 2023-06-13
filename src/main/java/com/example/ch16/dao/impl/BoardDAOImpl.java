package com.example.ch16.dao.impl;

import com.example.ch16.dao.BoardDAO;
import com.example.ch16.entity.Board;
import com.example.ch16.repository.BoardRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class BoardDAOImpl implements BoardDAO {

    private final BoardRepository boardRepository;

    public BoardDAOImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //입력
    @Override
    public Board insertBoard(Board board) {
        Board saveBoard = boardRepository.save(board);
        return saveBoard;
    }
    //수정
    @Override
    public Board updateBoard(Long id, String title, String contents) throws Exception {
        Optional<Board> selectBoard = boardRepository.findById(id);
        Board updateBoard;
        if (selectBoard.isPresent()) {
            Board board = selectBoard.get();
            board.setTitle(title);
            board.setContents(contents);
            board.setUpdatedAt(LocalDateTime.now());
            updateBoard = boardRepository.save(board);
        } else {
            throw new Exception();
        }
        return updateBoard;
    }
    //삭제
    @Override
    public void deleteBoard(Long id) throws Exception {
        Optional<Board> selectBoard = boardRepository.findById(id);
        if (selectBoard.isPresent()) {
            Board board = selectBoard.get();
            boardRepository.delete(board);
        } else {
            throw new Exception();
        }
    }
    //게시판 리스트
    @Override
    public List<Board> allBoard() {
        List<Board> allBoard = boardRepository.findAll();
        return allBoard;
    }
    //orderbycreate 리스트
    @Override
    public List<Board> listOrderByCreatedAt() {
        List<Board> listOrderByCreatedAt = boardRepository.findAllByOrderByCreatedAtDesc();
        return listOrderByCreatedAt;
    }
    //byuserid 리스트
    @Override
    public List<Board> listBoardByUserId(String userId) {
            return boardRepository.findByUserId(userId);
    }

    //게시판 정보
    @Override
    public Board selectBoard(Long id) {
        Board selectBoard = boardRepository.getReferenceById(id);
        return selectBoard;
    }
}
