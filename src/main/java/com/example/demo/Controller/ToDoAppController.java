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

    @RequestMapping("/task/{id}")
    public Optional<ToDoApp> getSpecificTask(@PathVariable Long id) {
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
    public String deleteSelectedTask(@PathVariable Long id){
        toDoAppServices.deleteTask(id);
        return "deleted";
    }

    @RequestMapping("/task")
    public List<ToDoApp> getAllOrSortOrFilter(@RequestParam(defaultValue = "") String sort, @RequestParam(defaultValue = "") String label, @RequestParam (defaultValue = "0") Long priority, @RequestParam(defaultValue = "Asc") String alignment) throws Exception {
        return toDoAppServices.getParameter(sort, label, priority, alignment);
    }
}
