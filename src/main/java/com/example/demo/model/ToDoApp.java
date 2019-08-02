package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class ToDoApp {

    @Id
    private int id;
    private String task;
    private String dueDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "ToDoApp{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}
