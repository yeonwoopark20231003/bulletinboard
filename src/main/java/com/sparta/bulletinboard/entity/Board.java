package com.sparta.bulletinboard.entity;


import com.sparta.bulletinboard.dto.BoardRequestDto;
import com.sparta.bulletinboard.dto.BoardResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class Board {
    private Long id;
    private String title;
    private String content;
    private String date;
    private String userId;  //Foreign key 설정
    private int password; // Foreign key 설정

    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.userId = requestDto.getUserId();
        //this.password = requestDto.getPassword();
        this.date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

    }

    public void modify(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.userId = requestDto.getUserId();
       // this.password = requestDto.getPassword();
        this.date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));


        }


    }

