package com.sparta.todolist.repository;

import com.sparta.todolist.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoList, Long>{
    List<TodoList> findAllByOrderByModifiedAtDesc();
}

