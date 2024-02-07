package com.prajwal.todo_app.service;

import com.prajwal.todo_app.model.Todo;
import com.prajwal.todo_app.repository.TodoRepository;
import com.prajwal.todo_app.util.CustomException;
import com.prajwal.todo_app.util.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository repo;

    public List<Todo> getAllTodos() {
        return repo.findAll();
    }

    public Todo createTodo(Todo todo) {
        return repo.save(todo);
    }

    public Todo getTodoById(int id) throws CustomException {
        Optional<Todo> todo = repo.findById(id);
        if (todo.isPresent()) return todo.get();
        throw CustomException.notFound("Todo With Id "+id+" Not Found");
    }

    public Todo updateStatus(int id, TaskStatus status) throws CustomException {
        Todo todo = getTodoById(id);
        todo.setStatus(status);
        repo.save(todo);
        return todo;
    }

    public List<Todo> fetchByStatus(TaskStatus status) {
        return repo.findByStatus(status);
    }

    public void deleteTodo(int id) throws CustomException {
        Todo todo = getTodoById(id);
        repo.delete(todo);
    }

    public Todo updateTodo(Todo todo) throws CustomException {
        Todo oldTodo = getTodoById(todo.getId());
        oldTodo.setTask(todo.getTask());
        oldTodo.setDescription(todo.getDescription());
        return repo.save(oldTodo);
    }
}
