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
import org.springframework.transaction.annotation.Transactional;

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
        return todoListRepository.findAllByOrderByModifiedAtDesc().stream().map(TodoListResponseDto::new).toList();
    }

    @Transactional
    public Long updateTodoList(Long id, TodoListRequestDto requestDto) {
        //해당 메모가 디비에 존재하는지 확인
        TodoList todoList=findTodo(id);
        //메모 내용 수정 => update라는 메서드를 todoList클래스에 만들어서 사용
        todoList.update(requestDto);

        return id;
    }

    public Long deleteTodoList(Long id) {
        //해당 메모가 DB에 존재하는지 확인
        TodoList todoList = findTodo(id);
        //메모 삭제
        todoListRepository.delete(todoList);

        return id;

    }


    private TodoList findTodo(Long id){
        return todoListRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 id는 존재하지 않습니다.")
        );
    }


}
