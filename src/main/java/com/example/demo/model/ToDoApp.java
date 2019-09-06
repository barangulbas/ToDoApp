package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "task")
public class ToDoApp {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public ToDoApp() {
    }

    @ManyToOne
    private User user;

    public ToDoApp(Long priority, Date dueDate, String task, String label, Boolean complete) {
        this.priority = priority;
        this.dueDate = dueDate;
        this.task = task;
        this.label = label;
        this.complete = complete;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                ", user=" + user +
                '}';
    }

}
