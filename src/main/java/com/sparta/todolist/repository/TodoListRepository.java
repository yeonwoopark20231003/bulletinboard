package com.sparta.todolist.repository;

import com.sparta.todolist.dto.TodoListRequestDto;
import com.sparta.todolist.dto.TodoListResponseDto;
import com.sparta.todolist.entity.TodoList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TodoListRepository {
    private final JdbcTemplate jdbcTemplate;

    public TodoListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public TodoList save(TodoList todoList) {
        //DB저장
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

        return todoList;
    }

    public List<TodoListResponseDto> findAll() {
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

    public void update(Long id, TodoListRequestDto requestDto) {
        TodoList todoList = findById(id);
        LocalDateTime currentDate = LocalDateTime.now();
        todoList.setDate(String.valueOf(currentDate));
        String sql = "UPDATE todolist SET title = ?, content = ?, date =? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getTitle(), requestDto.getContent(),currentDate, id);

    }

    public void delete(Long id) {
        String sql = "DELETE FROM todolist WHERE id=?";
        jdbcTemplate.update(sql, id);
    }


    public TodoList findById(Long id) {
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
