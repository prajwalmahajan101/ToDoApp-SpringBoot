package com.prajwal.todo_app.controller;

import com.prajwal.todo_app.dto.NewTodoDTO;
import com.prajwal.todo_app.dto.TodoDTO;
import com.prajwal.todo_app.dto.UpdateTodoDTO;
import com.prajwal.todo_app.service.TodoService;
import com.prajwal.todo_app.util.CustomException;
import com.prajwal.todo_app.util.CustomResponse;
import com.prajwal.todo_app.util.TaskStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService service;

    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public CustomResponse<List<TodoDTO>> geTodos() {
        return new CustomResponse<>(HttpStatus.OK, "Successfully Fetched List of Todos", service.getAllTodos());
    }

    @GetMapping("{id}")
    public CustomResponse<TodoDTO> getTodo(@PathVariable("id") int todoId) throws CustomException {
        TodoDTO todo = service.getTodoDTOById(todoId);
        return new CustomResponse<>(HttpStatus.OK, "Todo Successfully Fetched", todo);

    }

    @GetMapping("status/{status}")
    public CustomResponse<List<TodoDTO>> filterByStatus(@PathVariable("status") String status) throws CustomException {
        TaskStatus validStatus = TaskStatus.convert(status);
        List<TodoDTO> todos = service.fetchByStatus(validStatus);
        return new CustomResponse<>(HttpStatus.OK, "Todo with Status " + status + " fetched", todos);
    }

    @PostMapping
    public CustomResponse<TodoDTO> createTodo(@RequestBody @Valid NewTodoDTO todo) {
        return new CustomResponse<>(HttpStatus.CREATED, "Todo Successfully created", service.createTodo(todo));
    }

    @PatchMapping("{id}/status/{status}")
    public CustomResponse<TodoDTO> markTodo(@PathVariable("id") int todoId, @PathVariable("status") String status) throws CustomException {
        TaskStatus newStatus = TaskStatus.convert(status);
        TodoDTO todoDTO = service.updateStatus(todoId, newStatus);
        return new CustomResponse<>(HttpStatus.OK, "Todo Marked " + status, todoDTO);
    }

    @PutMapping
    public CustomResponse<TodoDTO> updateTodo(@RequestBody @Valid UpdateTodoDTO todoDto) throws CustomException {
        TodoDTO updatedTodo = service.updateTodo(todoDto);
        return new CustomResponse<>(HttpStatus.OK, "Todo Updated Successfully", updatedTodo);
    }

    @DeleteMapping("{id}")
    public CustomResponse<?> deleteTodo(@PathVariable("id") int id) throws CustomException {
        service.deleteTodo(id);
        return new CustomResponse<>(HttpStatus.OK, "Todo Deleted Successfully");

    }

}
