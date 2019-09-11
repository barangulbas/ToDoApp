package com.example.demo.repository;

import com.example.demo.model.ToDoApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface ToDoAppRepo extends JpaRepository<ToDoApp, Long> {

    List<ToDoApp> findByOrderByPriorityDesc();
    List<ToDoApp> findByOrderByPriorityAsc() ;
    List<ToDoApp> findByOrderByTaskAsc() ;
    List<ToDoApp> findByOrderByDueDateAsc();
    List<ToDoApp> findByOrderByDueDateDesc();
    List<ToDoApp> findByLabel(String label);
    List<ToDoApp> findByPriority(Long priority);
    List<ToDoApp> findByDueDate(Date date);
}