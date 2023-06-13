package com.example.ch16.controller;

import com.example.ch16.config.security.JwtTokenProvider;
import com.example.ch16.dto.*;
import com.example.ch16.entity.Board;
import com.example.ch16.entity.User;
import com.example.ch16.service.BoardService;
import com.example.ch16.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public BoardController(BoardService boardService, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.boardService = boardService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    //입력
    @PostMapping()
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardDto boardDto) {
        BoardResponseDto boardResponseDto = boardService.saveBoard(boardDto);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    //수정
    @PutMapping()
    public ResponseEntity<BoardResponseDto> changeboard(@RequestBody ChangeBoardDto changeBoardDto) throws Exception {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principle;
        BoardResponseDto boardResponseDto = boardService.getBoard(changeBoardDto.getId());

        if (user.getUid().equals(boardResponseDto.getUserId())) {
            BoardResponseDto boardResponseDto1 = boardService.changeBoard(
                    changeBoardDto.getId(), changeBoardDto.getTitle(), changeBoardDto.getContents());
            return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto1);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    //삭제
    @DeleteMapping()
    public ResponseEntity<String> deleteBoard(Long id) throws Exception {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principle;
        BoardResponseDto boardResponseDto = boardService.getBoard(id);
        if (user.getUid().equals(boardResponseDto.getUserId())) {
        boardService.deletBoard(id);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    //게시판 리스트
    @GetMapping("/list")
    public ResponseEntity<List<BoardResponseDto>> allBoard() {
        List<BoardResponseDto> boardResponseDto = boardService.allBoard();
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }
    //orderbycreate 리스트
    @GetMapping("/listOrderByCreatedAt")
    public ResponseEntity<List<BoardResponseDto>> listOrderByCreatedAt() {
        List<BoardResponseDto> boardResponseDto = boardService.listOrderByCreatedAt();
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }
    //byuserid 리스트
    @GetMapping("/byUserId")
    public ResponseEntity<List<BoardResponseDto>> listByUserId(String userId) {
        List<BoardResponseDto> boardResponseDto = boardService.listByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }
    //게시글 정보
    @GetMapping("/")
    public ResponseEntity<BoardResponseDto> getProduct(Long id) {
        BoardResponseDto boardResponseDto = boardService.getBoard(id);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }
}
