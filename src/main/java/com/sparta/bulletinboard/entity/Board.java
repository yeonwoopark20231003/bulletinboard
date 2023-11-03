package com.sparta.bulletinboard.entity;


import com.sparta.bulletinboard.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Board {
    private Long id;
    private String title;
    private String content;
    private int date;
    private String userId;  //Foreign key 설정
    private int password; // Foreign key 설정

    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
