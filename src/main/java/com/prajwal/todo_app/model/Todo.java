package com.prajwal.todo_app.model;

import com.prajwal.todo_app.util.TaskStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String task;

    @Column(nullable = false, length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.not_started;

    public Todo() {
    }

    public Todo(int id, String task, String description, TaskStatus status) {
        this.id = id;
        this.task = task;
        this.description = description;
        this.status = status;
    }


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
