package com.sparta.bulletinboard.dto;

import com.sparta.bulletinboard.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private int date;
    private String userId;  //Foreign key 설정
    private int password; // Foreign key 설정

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
