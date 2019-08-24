package com.example.demo.Controller;

import com.example.demo.Services.ToDoAppServices;
import com.example.demo.model.ToDoApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/task")
public class ToDoAppController {

    @Autowired
    private ToDoAppServices toDoAppServices;

    @GetMapping("/{id}")
    public Optional<ToDoApp> getSpecificTask(@PathVariable Long id) {
        return toDoAppServices.getTask(id);
    }

    @PostMapping("/create")
    public ToDoApp createNewTask(@RequestBody ToDoApp task) throws Exception{
        return toDoAppServices.createTask(task);
    }

    @PutMapping("/edit")
    public ToDoApp editTask(@RequestBody ToDoApp task) {
        return toDoAppServices.saveOrUpdateTask(task);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSelectedTask(@PathVariable Long id){
        toDoAppServices.deleteTask(id);
        return "deleted";
    }

    @GetMapping("/complete")
    public void completeOrNot(@RequestParam(defaultValue = "false")Boolean complete){

    }

    @GetMapping("")
    public List<ToDoApp> getAllOrSortOrFilter(@RequestParam(required = false) String sort,
                                              @RequestParam(required = false) String label,
                                              @RequestParam (required = false) Long priority,
                                              @RequestParam(required = false) String dueDate,
                                              @RequestParam(defaultValue = "Asc") String alignment) throws Exception {
        return toDoAppServices.getParameter(sort, label, priority, dueDate, alignment);
    }
}
