package com.sparta.bulletinboard.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String userId;
    private int password;
    private String title;
    private String content;
}
