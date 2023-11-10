package com.sparta.todolist.entity;


import com.sparta.todolist.dto.TodoListRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class TodoList {
    private Long id;
    private String title;
    private String content;
    private String date;
    private String userId;  //Foreign key 설정
    private String password; // Foreign key 설정

    public TodoList(TodoListRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.userId = requestDto.getUserId();
        this.password = requestDto.getPassword();
        this.date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

    }

    public void modify(TodoListRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.userId = requestDto.getUserId();
        // this.password = requestDto.getPassword();
        this.date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));


    }

}