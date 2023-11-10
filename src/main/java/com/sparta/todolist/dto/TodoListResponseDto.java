package com.sparta.todolist.dto;

import com.sparta.todolist.entity.TodoList;
import lombok.Getter;

@Getter
public class TodoListResponseDto {
    private Long id;
    private String title;
    private String content;
   // private String date;
    private String userId;  //Foreign key 설정
    private String password; // Foreign key 설정

    public TodoListResponseDto(TodoList todoList) {
        this.id = todoList.getId();
        this.title = todoList.getTitle();
        this.content = todoList.getContent();
        //this.date = todoList.getDate();
        this.userId = todoList.getUserId();
        //this.password = board.getPassword();
    }

    public TodoListResponseDto(Long id, String title, String content,String date, String userId, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        //this.date = date;
        this.userId = userId;
        this.password = password;
    }
}
