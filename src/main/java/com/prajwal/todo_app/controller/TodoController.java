package com.prajwal.todo_app.controller;

import com.prajwal.todo_app.model.Todo;
import com.prajwal.todo_app.service.TodoService;
import com.prajwal.todo_app.util.Response;
import com.prajwal.todo_app.util.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping
    public ResponseEntity<Response<List<Todo>>> geTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(new Response<>("Successfully Fetched List of Todos", service.getAllTodos()));
    }

    @GetMapping("{id}")
    public ResponseEntity<Response<Todo>> getTodo(@PathVariable("id") int todoId) {
        try {
            Todo todo = service.getTodoById(todoId);
            return ResponseEntity.status(HttpStatus.OK).body(new Response<>("Todo Successfully Fetched", todo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>(e));
        }
    }

    @GetMapping("status/{status}")
    public ResponseEntity<Response<List<Todo>>> filterByStatus(@PathVariable("status") String status) {
        try {
            TaskStatus validStatus = TaskStatus.valueOf(status);
            return ResponseEntity.status(HttpStatus.OK).body(new Response<>("Todo with Status " + status + " fetched", service.fetchByStatus(validStatus)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new Response<>(new Exception("Invalid Status")));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>(e));
        }
    }

    @PostMapping
    @ResponseStatus(code= HttpStatus.CREATED)
    public Response<Todo> createTodo(@RequestBody Todo todo) {
        return new Response<>("Todo Successfully created", service.createTodo(todo));
    }

    @PatchMapping("{id}/status/{status}")
    public ResponseEntity<Response<Todo>> markTodo(@PathVariable("id") int todoId, @PathVariable("status") String status) {
        try {
            TaskStatus newStatus = TaskStatus.valueOf(status);
            Todo todo = service.updateStatus(todoId, newStatus);
            return ResponseEntity.status(HttpStatus.OK).body(new Response<>("Todo Marked " + status, todo));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new Response<>(new Exception("Invalid Status")));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>(e));
        }
    }

    @PutMapping
    public ResponseEntity<Response<Todo>> updateTodo(@RequestBody Todo todo){
        try {
            Todo updatedTodo = service.updateTodo(todo);
            return ResponseEntity.status(HttpStatus.OK).body(new Response<>("Todo Updated Successfully", updatedTodo));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>(e));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response<String>> deleteTodo(@PathVariable("id") int id) {
        try {
            service.deleteTodo(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response<>("Todo Deleted Successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>(e));
        }
    }

}
