package com.sparta.todolist.entity;


import com.sparta.todolist.dto.TodoListRequestDto;
import com.sparta.todolist.dto.TodoListResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@Table(name="todolist")
@NoArgsConstructor
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false, length = 500)
    private String content;
    @Column(name = "date", nullable = false)
    private String date;
    @Column(name = "userId", nullable = false)
    private String userId;  //Foreign key 설정
    @Column(name = "password", nullable = false)
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