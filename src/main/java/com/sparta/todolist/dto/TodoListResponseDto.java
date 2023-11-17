package com.sparta.todolist.dto;

import com.sparta.todolist.entity.TodoList;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoListResponseDto {
    private Long id;
    private String title;
    private String content;
    private String userId;  //Foreign key 설정
    private String password; // Foreign key 설정
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public TodoListResponseDto(TodoList todoList) {
        this.id = todoList.getId();
        this.title = todoList.getTitle();
        this.content = todoList.getContent();
        this.userId = todoList.getUserId();
        //this.password = board.getPassword();
        this.createdAt = todoList.getCreatedAt();
        this.modifiedAt = todoList.getModifiedAt();
    }

}
