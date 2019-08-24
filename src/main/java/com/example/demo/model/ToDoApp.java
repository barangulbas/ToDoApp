package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class ToDoApp {


    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;
    @Column(name="priority")
    private Long priority;
    @Column(name="dueDate")
    private Date dueDate;
    @Column(name="task")
    private String task;
    @Column(name="label")
    private String label;
    @Column(name="complete")
    private Boolean complete;

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

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "ToDoApp{" +
                "id=" + id +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", task='" + task + '\'' +
                ", label='" + label + '\'' +
                ", complete=" + complete +
                '}';
    }
}
