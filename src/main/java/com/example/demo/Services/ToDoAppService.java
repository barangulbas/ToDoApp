package com.example.demo.Services;

import com.example.demo.Repositories.ToDoAppRepo;

import com.example.demo.model.ToDoApp;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ToDoAppService {

    private ToDoAppRepo toDoAppRepo;

    @Autowired
    public ToDoAppService(ToDoAppRepo toDoAppRepo){
        this.toDoAppRepo = toDoAppRepo;
    }

    public List<ToDoApp> getTasks(){
        return toDoAppRepo.findAll();
    }

    public ToDoApp getTask(Long id) {
        return toDoAppRepo.getOne(id);
    }

    public ToDoApp createTask(ToDoApp task, User auth) throws Exception {
        if (task.getTask().isEmpty()) {
            throw new Exception("Shouldn't be empty");
        }
        task.setUser(auth);
        try {
            toDoAppRepo.save(task);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return task;
    }

    public ToDoApp saveOrUpdateTask(ToDoApp task, Long id){
        return toDoAppRepo.findById(id)
                .map(newTask ->{
                    newTask.setComplete(task.getComplete());
                    newTask.setDueDate(task.getDueDate());
                    newTask.setPriority(task.getPriority());
                    return toDoAppRepo.save(newTask);
                })
                .orElseGet(()->{
                    task.setId(id);
                    return toDoAppRepo.save(task);
                });
    }

    public void deleteTask(Long id){

        ToDoApp deletedTask = toDoAppRepo.getOne(id);
        toDoAppRepo.delete(deletedTask);
    }

    public List<ToDoApp> getParameter(String sort, String label, Long priority, String dueDate, String alignment) throws Exception {

        if(sort != null ){
            return sortBy(sort, alignment);
        }
        else if(label != null){
            return filterByLabel(label);
        }
        else if(priority != null){
            return filterByPriority(priority);
        }
        else if(dueDate != null){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = df.parse(dueDate);
            return filterByDueDate(date);
        }
        else{
            return getTasks();
        }
    }
    public List<ToDoApp> sortBy(String sort, String alignment) throws Exception {

        if (sort.equals("priority")) {
            if (alignment.equals("Asc")) {
                return toDoAppRepo.findByOrderByPriorityAsc();
            } else if (alignment.equals("Desc")) {
                return toDoAppRepo.findByOrderByPriorityDesc();
            } else {
                throw new Exception("Type 'Asc' or 'Desc' ");
            }
        }
        else if (sort.equals("task")) {
            if (alignment.equals("Asc")) {
                return toDoAppRepo.findByOrderByTaskAsc();
            }
            else{
                throw new Exception("Only option is 'Asc' ");
            }
        }
        else if(sort.equals("dueDate")){
            if (alignment.equals("Asc")) {
                return toDoAppRepo.findByOrderByDueDateAsc();
            } else if (alignment.equals("Desc")) {
                return toDoAppRepo.findByOrderByDueDateDesc();
            } else {
                throw new Exception("Type 'Asc' or 'Desc' ");
            }
        }
        else{
            throw new Exception("Type 'priority' || 'task' || 'dueDate' ");
        }
    }

    public List<ToDoApp> filterByLabel(String label){

        return toDoAppRepo.findByLabel(label);
    }

    public List<ToDoApp> filterByPriority(Long priority){
        return toDoAppRepo.findByPriority(priority);
    }

    public List<ToDoApp> filterByDueDate(Date date) {

        return toDoAppRepo.findByDueDate(date);
    }


}
