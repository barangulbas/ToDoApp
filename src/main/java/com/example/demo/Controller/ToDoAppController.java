package com.example.demo.Controller;

import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.ToDoAppService;
import com.example.demo.model.ToDoApp;
import com.example.demo.model.User;
import com.example.demo.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class ToDoAppController {

    @Autowired
    private ToDoAppService toDoAppServices;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ToDoApp getSpecificTask(@PathVariable Long id) {
        return toDoAppServices.getTask(id);
    }

    @PostMapping("/create")
    public ToDoApp createNewTask(@RequestBody ToDoApp task) throws Exception{
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User user = userRepository.getOne(userPrincipal.getId());
        return toDoAppServices.createTask(task, user);
    }

    @PutMapping("/edit/{id}")
    public ToDoApp editTask(@RequestBody ToDoApp task, @PathVariable Long id) {
        return toDoAppServices.saveOrUpdateTask(task,id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSelectedTask(@PathVariable Long id){
        toDoAppServices.deleteTask(id);
        return "deleted";
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
