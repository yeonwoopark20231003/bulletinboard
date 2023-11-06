package com.sparta.bulletinboard.dto;

import com.sparta.bulletinboard.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String date;
    private String userId;  //Foreign key 설정
    private int password; // Foreign key 설정

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.date = board.getDate();
        this.userId = board.getUserId();
        //this.password = board.getPassword();
    }
}
