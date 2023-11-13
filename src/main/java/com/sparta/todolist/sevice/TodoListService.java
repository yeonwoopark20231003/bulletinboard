package com.sparta.todolist.sevice;

import com.sparta.todolist.dto.TodoListRequestDto;
import com.sparta.todolist.dto.TodoListResponseDto;
import com.sparta.todolist.entity.TodoList;
import com.sparta.todolist.repository.TodoListRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoListService {

    private final TodoListRepository todoListRepository;
    public TodoListService(TodoListRepository todoListRepository){
        this.todoListRepository = todoListRepository;
    }

    public TodoListResponseDto createTodoList(TodoListRequestDto requestDto) {
        //RequestDto -> Entity로 저장해야함!
        TodoList todoList = new TodoList(requestDto);

        //DB저장
        TodoList saveTodoList = todoListRepository.save(todoList);

        //Entity -> ResponseDto
        TodoListResponseDto todoListResponseDto = new TodoListResponseDto(todoList);

        return todoListResponseDto;
    }

    public List<TodoListResponseDto> getTodoList() {
        // DB 조회
        return todoListRepository.findAll();
    }


    public Long updateTodoList(Long id, TodoListRequestDto requestDto) {
        //해당 메모가 디비에 존재하는지 확인
        TodoList todoList = todoListRepository.findById(id);

        if (todoList != null) {
            //메모 내용 수정
            todoListRepository.update(id, requestDto);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }

    }

    public Long deleteTodoList(Long id) {
        //해당 메모가 DB에 존재하는지 확인
        TodoList todoList = todoListRepository.findById(id);

        if (todoList != null) {
            todoListRepository.delete(id);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }





}
