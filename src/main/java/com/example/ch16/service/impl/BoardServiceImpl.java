package com.example.ch16.service.impl;

import com.example.ch16.dao.BoardDAO;
import com.example.ch16.dto.BoardDto;
import com.example.ch16.dto.BoardResponseDto;
import com.example.ch16.dto.ProductResponseDto;
import com.example.ch16.entity.Board;
import com.example.ch16.entity.Product;
import com.example.ch16.entity.User;
import com.example.ch16.service.BoardService;
import com.example.ch16.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;
    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }
    @Override
    public BoardResponseDto saveBoard( BoardDto boardDto) {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principle;

        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContents(boardDto.getContents());
        board.setUserId(user.getUid());
        board.setUserName(user.getName());
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());

        Board saveBoard = boardDAO.insertBoard(board);

        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(saveBoard.getId());
        boardResponseDto.setTitle(saveBoard.getTitle());
        boardResponseDto.setContents(saveBoard.getContents());
        boardResponseDto.setUserId(saveBoard.getUserId());
        boardResponseDto.setUserName(saveBoard.getUserName());
        return boardResponseDto;
    }

    //수정
    @Override
    public BoardResponseDto changeBoard(Long id, String title, String contents) throws Exception {
        Board changeBoard = boardDAO.updateBoard(id, title, contents);
        return new BoardResponseDto(changeBoard);
    }
    //삭제
    @Override
    public void deletBoard(Long id) throws Exception {
        boardDAO.deleteBoard(id);
    }
    //게시판 리스트
    @Override
    public List<BoardResponseDto> allBoard() {
        List<Board> boards = boardDAO.allBoard();
        List<BoardResponseDto> boardResponseDtoList = boards.stream().map(board -> new BoardResponseDto(board)).collect(Collectors.toList());
        return boardResponseDtoList;
    }
    //orderbycreate 리스트
    @Override
    public List<BoardResponseDto> listOrderByCreatedAt() {
        List<Board> boards = boardDAO.listOrderByCreatedAt();
        List<BoardResponseDto> boardResponseDtoList = boards.stream().map(board -> new BoardResponseDto(board)).collect(Collectors.toList());
        return boardResponseDtoList;
    }
    //byuserid 리스트
    @Override
    public List<BoardResponseDto> listByUserId(String userId) {
        List<Board> boards = boardDAO.listBoardByUserId(userId);
        List<BoardResponseDto> boardResponseDtoList = boards.stream().map(board -> new BoardResponseDto(board)).collect(Collectors.toList());
        return boardResponseDtoList;
    }
    //게시판 정보
    @Override
    public BoardResponseDto getBoard(Long id) {
        Board board = boardDAO.selectBoard(id);

        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(board.getId());
        boardResponseDto.setTitle(board.getTitle());
        boardResponseDto.setContents(board.getContents());
        boardResponseDto.setUserId(board.getUserId());
        boardResponseDto.setUserName(board.getUserName());

        return boardResponseDto;
    }


}
