package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ToDoApp {

    @Id
    private int id;
    private int priority;
    private String task;


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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "ToDoApp{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
