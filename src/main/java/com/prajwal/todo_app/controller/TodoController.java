package com.prajwal.todo_app.controller;

import com.prajwal.todo_app.model.Todo;
import com.prajwal.todo_app.service.TodoService;
import com.prajwal.todo_app.util.CustomResponse;
import com.prajwal.todo_app.util.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping
    public CustomResponse<List<Todo>> geTodos() {
        return new CustomResponse<>(HttpStatus.OK,"Successfully Fetched List of Todos", service.getAllTodos());
    }

    @GetMapping("{id}")
    public CustomResponse<Todo> getTodo(@PathVariable("id") int todoId) {
        try {
            Todo todo = service.getTodoById(todoId);
            return new CustomResponse<>(HttpStatus.OK,"Todo Successfully Fetched", todo);
        } catch (Exception e) {
            return new CustomResponse<>(HttpStatus.NOT_FOUND,e);
        }
    }

    @GetMapping("status/{status}")
    public CustomResponse<List<Todo>> filterByStatus(@PathVariable("status") String status) {
        try {
            TaskStatus validStatus = TaskStatus.valueOf(status);
            return new CustomResponse<>(HttpStatus.OK,"Todo with Status " + status + " fetched", service.fetchByStatus(validStatus));
        } catch (IllegalArgumentException e) {
            return new CustomResponse<>(HttpStatus.UNPROCESSABLE_ENTITY,new Exception("Invalid Status"));
        } catch (Exception e) {
            return new CustomResponse<>(HttpStatus.NOT_FOUND,e);
        }
    }

    @PostMapping
    public CustomResponse<Todo> createTodo(@RequestBody Todo todo) {
        return new CustomResponse<>(HttpStatus.CREATED,"Todo Successfully created", service.createTodo(todo));
    }

    @PatchMapping("{id}/status/{status}")
    public CustomResponse<Todo> markTodo(@PathVariable("id") int todoId, @PathVariable("status") String status) {
        try {
            TaskStatus newStatus = TaskStatus.valueOf(status);
            Todo todo = service.updateStatus(todoId, newStatus);
            return new CustomResponse<>(HttpStatus.OK,"Todo Marked " + status, todo);
        } catch (IllegalArgumentException e) {
            return new CustomResponse<>(HttpStatus.UNPROCESSABLE_ENTITY,new Exception("Invalid Status"));
        } catch (Exception e) {
            return new CustomResponse<>(HttpStatus.NOT_FOUND,e);
        }
    }

    @PutMapping
    public CustomResponse<Todo> updateTodo(@RequestBody Todo todo){
        try {
            Todo updatedTodo = service.updateTodo(todo);
            return new CustomResponse<>(HttpStatus.OK,"Todo Updated Successfully", updatedTodo);
        }catch (Exception e){
            return new CustomResponse<>(HttpStatus.NOT_FOUND,e);
        }
    }

    @DeleteMapping("{id}")
    public CustomResponse<Todo> deleteTodo(@PathVariable("id") int id) {
        try {
            service.deleteTodo(id);
            return new CustomResponse<>(HttpStatus.OK,"Todo Deleted Successfully");
        } catch (Exception e) {
            return new CustomResponse<>(HttpStatus.NOT_FOUND,e);
        }
    }

}
