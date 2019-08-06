package com.example.demo.Services;

import com.example.demo.Repositories.ToDoAppRepo;
import com.example.demo.model.ToDoApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ToDoAppServices {

    @Autowired
    public ToDoAppRepo toDoAppRepo;


    public ToDoAppServices(){

    }

    public List<ToDoApp> getTasks(){
        return toDoAppRepo.findAll();
    }

    public Optional<ToDoApp> getTask(Long id) {
        return toDoAppRepo.findById(id);
    }

    public ToDoApp createTask(ToDoApp task) throws Exception {
        if (task.getTask().isEmpty()){
            throw new Exception("Shouldn't be empty");
        }
        toDoAppRepo.save(task);
        return task;
    }

    public ToDoApp saveOrUpdateTask(ToDoApp task){
        toDoAppRepo.save(task);
        return task;
    }

    public void deleteTask(Long id){
        ToDoApp tsk = toDoAppRepo.getOne(id);
        toDoAppRepo.delete(tsk);
    }

    public List<ToDoApp> getParameter(String sort, String label, Long priority, String alignment) throws Exception {
        if(!sort.equals("")){
            return sortBy(sort, alignment);
        }
        else if(!label.equals("")){
            return filterByLabel(label,priority);
        }
        else if(priority!=0){
            return filterByPriority(priority,label);
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
        } else if (sort.equals("task")) {
            if (alignment.equals("Asc")) {
                return toDoAppRepo.findByOrderByTaskAsc();
            }
            else{
                throw new Exception("Only option is 'Asc' ");
            }
        }
        else{
            throw new Exception("Type 'priority' or 'task' ");
        }
    }

    public List<ToDoApp> filterByLabel(String label, Long priority){

        return toDoAppRepo.findByLabel(label);
    }

    public List<ToDoApp> filterByPriority(Long priority,String label){
        return toDoAppRepo.findByPriority(priority);
    }


}
