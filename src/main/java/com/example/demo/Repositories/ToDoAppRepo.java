package com.example.demo.Repositories;

import com.example.demo.model.ToDoApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoAppRepo extends JpaRepository<ToDoApp, Integer> {
}