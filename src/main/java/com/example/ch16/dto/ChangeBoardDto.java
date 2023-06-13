package com.example.ch16.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChangeBoardDto {
    private long id;
    private String title;
    private String contents;

}
