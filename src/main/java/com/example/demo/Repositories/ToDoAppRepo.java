package com.example.demo.Repositories;

import com.example.demo.model.ToDoApp;
import com.tdunning.math.stats.Sort;
import org.jboss.logging.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ToDoAppRepo extends JpaRepository<ToDoApp, Long> {

    List<ToDoApp> findByOrderByPriorityDesc();
    List<ToDoApp> findByOrderByPriorityAsc() ;
    List<ToDoApp> findByOrderByTaskAsc() ;
    List<ToDoApp> findByLabel(String label);
    List<ToDoApp> findByPriority(Long priority);
}