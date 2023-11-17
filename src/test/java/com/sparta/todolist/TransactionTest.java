package com.sparta.todolist;

import com.sparta.todolist.entity.TodoList;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransactionTest {
    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("리스트 생성 성공")
    void test1(){
        TodoList todoList = new TodoList();
        todoList.setTitle("투두리스트 제목");
        todoList.setContent("@Transcational 테스트");
        todoList.setDate("231117");
        todoList.setUserId("Yeonwoo");
        todoList.setPassword("1234");

        em.persist(todoList);

    }
}
