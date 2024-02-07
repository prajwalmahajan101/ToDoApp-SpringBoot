package com.prajwal.todo_app.service;

import com.prajwal.todo_app.dto.NewTodoDTO;
import com.prajwal.todo_app.dto.TodoDTO;
import com.prajwal.todo_app.dto.UpdateTodoDTO;
import com.prajwal.todo_app.util.CustomException;
import com.prajwal.todo_app.util.TaskStatus;

import java.util.List;

public interface TodoService {
    List<TodoDTO> getAllTodos();

    TodoDTO createTodo(NewTodoDTO newTodoDTO);

    TodoDTO getTodoById(int id) throws CustomException;

    TodoDTO updateStatus(int id, TaskStatus status) throws CustomException;

    List<TodoDTO> fetchByStatus(TaskStatus status);

    void deleteTodo(int id) throws CustomException;

    TodoDTO updateTodo(UpdateTodoDTO todo) throws CustomException;
}
