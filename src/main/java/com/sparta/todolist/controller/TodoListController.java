package com.sparta.todolist.controller;

import com.sparta.todolist.dto.TodoListRequestDto;
import com.sparta.todolist.dto.TodoListResponseDto;
import com.sparta.todolist.sevice.TodoListService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoListController {


    private final TodoListService todoListService;
    public TodoListController(TodoListService todoListService){
        this.todoListService = todoListService;

    }


    @PostMapping("/todolist")
    public TodoListResponseDto createTodoList(@RequestBody TodoListRequestDto requestDto) {
        return todoListService.createTodoList(requestDto);
    }

    @GetMapping("/todolist")
    public List<TodoListResponseDto> getTodoList() {
        return todoListService.getTodoList();
    }


    @PutMapping("/todolist/{id}")
    public Long updateTodoList(@PathVariable Long id, @RequestBody TodoListRequestDto requestDto) {
        return todoListService.updateTodoList(id,requestDto);
    }


    @DeleteMapping("/todolist/{id}")
    public Long deleteTodoList(@PathVariable Long id) {
        return todoListService.deleteTodoList(id);
    }

}


