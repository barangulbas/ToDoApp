package com.example.demo.Controller;

import com.example.demo.Services.ToDoAppServices;
import com.example.demo.model.ToDoApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ToDoAppController {

    @Autowired
    private ToDoAppServices toDoAppServices;

    @RequestMapping(path="/task")
    public List<ToDoApp> getAllTasks(){
        return toDoAppServices.getTasks();
    }

    @RequestMapping("/task/{id}")
    public Optional<ToDoApp> getSpecificTask(@PathVariable int id) {
        return toDoAppServices.getTask(id);
    }

    @PostMapping("/task/create")
    public ToDoApp createNewTask(@RequestBody ToDoApp task) throws Exception{
        return toDoAppServices.createTask(task);
    }

    @PutMapping("/task/edit")
    public ToDoApp editTask(@RequestBody ToDoApp task) {
        return toDoAppServices.saveOrUpdateTask(task);
    }

    @DeleteMapping("/task/delete/{id}")
    public String deleteSelectedTask(@PathVariable int id){
        toDoAppServices.deleteTask(id);
        return "deleted";
    }

    @RequestMapping("/task/sortByPriority")
    public List<ToDoApp> sortByPriority(){
        return toDoAppServices.sortByPriority();
    }

    @RequestMapping("/task/sortByTask")
    public List<ToDoApp> sortByTask(){
        return toDoAppServices.sortByTask();
    }



}
