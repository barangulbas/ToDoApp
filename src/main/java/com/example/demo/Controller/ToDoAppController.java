package com.example.demo.Controller;

import com.example.demo.Services.ToDoAppServices;
import com.example.demo.model.JwtUser;
import com.example.demo.model.ToDoApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PutMapping(value = "/task/edit/{id}", consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                MediaType.APPLICATION_XML_VALUE,
                MediaType.APPLICATION_JSON_VALUE
                })
    public ToDoApp editTask(@Valid @RequestBody ToDoApp task, @PathVariable Long id) {
        return toDoAppServices.saveOrUpdateTask(task, id);
    }

    @DeleteMapping("/task/delete/{id}")
    public String deleteSelectedTask(@PathVariable Long id){
        toDoAppServices.deleteTask(id);
        return "deleted";
    }

    @RequestMapping("/task/complete")
    public void completeOrNot(@RequestParam(defaultValue = "false")Boolean complete){

    }
    @RequestMapping("/task")
    public List<ToDoApp> getAllOrSortOrFilter(@RequestParam(defaultValue = "") String sort,
                                              @RequestParam(defaultValue = "") String label,
                                              @RequestParam (defaultValue = "0") Long priority,
                                              @RequestParam(defaultValue = "") String dueDate,
                                              @RequestParam(defaultValue = "Asc") String alignment) throws Exception {
        return toDoAppServices.getParameter(sort, label, priority, dueDate, alignment);
    }

    @RequestMapping("/signin")
    public String successful(){
        return "Hello ";
    }
}
