package com.example.demo.Repositories;

import com.example.demo.model.ToDoApp;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ToDoAppRepo extends JpaRepository<ToDoApp, Integer> {

    List<ToDoApp> findByOrderByPriorityDesc();
    List<ToDoApp> findByOrderByPriorityAsc() ;
    List<ToDoApp> findByOrderByTaskAsc() ;
}