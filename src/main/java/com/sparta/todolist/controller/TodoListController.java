package com.sparta.todolist.controller;

import com.sparta.todolist.dto.TodoListRequestDto;
import com.sparta.todolist.dto.TodoListResponseDto;
import com.sparta.todolist.entity.TodoList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api")
public class TodoListController {


    private final JdbcTemplate jdbcTemplate;

    public TodoListController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

   // private final Map<Long, TodoList> TodoListList = new HashMap<>();

    @PostMapping("/todolist")
    public TodoListResponseDto createTodoList(@RequestBody TodoListRequestDto requestDto) {
        //RequestDto -> Entity로 저장해야함!
        TodoList todoList = new TodoList(requestDto);

//
//        //Board Max Id찾기
//        Long maxId = TodoListList.size() > 0 ? Collections.max(TodoListList.keySet()) + 1 : 1;
//        todoList.setId(maxId);

        //DB저장
        //TodoListList.put(todoList.getId(), todoList);
        KeyHolder keyHolder = new GeneratedKeyHolder();// 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO todolist (title, content, userId, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, todoList.getTitle());
                    preparedStatement.setString(2, todoList.getContent());
                    preparedStatement.setString(3, todoList.getUserId());
                    preparedStatement.setString(4, todoList.getPassword());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        todoList.setId(id);

//        //날짜 저장하기
//        LocalDate todaydate = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        String fommatedTodaydate = todaydate.format(formatter);
//        todoList.setDate(fommatedTodaydate);

        //Entity -> ResponseDto
        TodoListResponseDto todoListResponseDto = new TodoListResponseDto(todoList);

        return todoListResponseDto;
    }

    @GetMapping("/todolist")
    public List<TodoListResponseDto> getTodoList() {
//        // Map to List
//        List<TodoListResponseDto> responseList = TodoListList.values().stream()
//                .map(TodoListResponseDto::new).toList();
//
//        return responseList;
//    }

        // DB 조회
        String sql = "SELECT * FROM todolist";

        return jdbcTemplate.query(sql, new RowMapper<TodoListResponseDto>() {
            @Override
            public TodoListResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 todolist 데이터들을 TodolistResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String date = rs.getString("date");
                String userId = rs.getString("userId");
                String password = rs.getString("password");
                return new TodoListResponseDto(id, title, content,userId,date,password);
            }
        });
    }


    @PutMapping("/todolist/{id}")
    public Long modifyTodoList(@PathVariable Long id, @RequestBody TodoListRequestDto requestDto) {
        //해당 메모가 디비에 존재하는지 확인
        TodoList todoList = findById(id);


        if (todoList != null) {
            LocalDateTime currentDate = LocalDateTime.now();
            todoList.setDate(String.valueOf(currentDate));


            String sql = "UPDATE todolist SET title = ?, content = ?, date =? WHERE id = ?";
            jdbcTemplate.update(sql, requestDto.getTitle(), requestDto.getContent(),currentDate, id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }


    @DeleteMapping("/todolist/{id}")
    public Long deleteTodoList(@PathVariable Long id) {
        //해당 메모가 DB에 존재하는지 확인
        TodoList todoList = findById(id);
        if (todoList != null) {
            String sql = "DELETE FROM todolist WHERE id=?";
            jdbcTemplate.update(sql, id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
    private TodoList findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM todolist WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                TodoList todoList = new TodoList();
                todoList.setTitle(resultSet.getString("title"));
                todoList.setContent(resultSet.getString("content"));
                return todoList;
            } else {
                return null;
            }
        }, id);
    }
}


