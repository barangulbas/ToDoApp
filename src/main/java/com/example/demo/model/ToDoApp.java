package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class ToDoApp {


    @Id
    @GeneratedValue
    private Long id;
    private Long priority;
    private Date dueDate;
    private String task;
    private String label;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "ToDoApp{" +
                "id=" + id +
                ", priority=" + priority +
                ", dueDate='" + dueDate.getTime() + '\'' +
                ", task='" + task + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
