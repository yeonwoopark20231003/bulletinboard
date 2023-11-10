package com.sparta.todolist.dto;

import lombok.Getter;

@Getter
public class TodoListRequestDto {
    private String userId;
    private String password;
    private String title;
    private String content;
}
