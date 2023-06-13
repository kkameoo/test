package com.example.ch16.dto;

import com.example.ch16.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String userId;
    private String userName;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.userId = board.getUserId();
        this.userName = board.getUserName();
    }
}
