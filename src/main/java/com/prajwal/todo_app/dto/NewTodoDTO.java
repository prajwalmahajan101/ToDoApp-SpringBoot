package com.prajwal.todo_app.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.prajwal.todo_app.util.TrimDeserializer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NewTodoDTO {
    @NotNull(message = "Task is mandatory")
    @NotBlank(message = "Task is mandatory")
    @Size(min=8,max=250,message = "Task must be between 8 to 250 characters")
    @JsonDeserialize(using = TrimDeserializer.class)
    private String task;
    @NotNull(message = "Description is mandatory")
    @NotBlank(message = "Description is mandatory")
    @Size(min=8,max=450,message = "Description must be between 8 to 450 characters")
    @JsonDeserialize(using = TrimDeserializer.class)
    private String description;

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
}
