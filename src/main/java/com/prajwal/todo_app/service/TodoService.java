package com.prajwal.todo_app.service;

import com.prajwal.todo_app.converter.TodoConverter;
import com.prajwal.todo_app.dto.NewTodoDTO;
import com.prajwal.todo_app.dto.TodoDTO;
import com.prajwal.todo_app.dto.UpdateTodoDTO;
import com.prajwal.todo_app.model.Todo;
import com.prajwal.todo_app.repository.TodoRepository;
import com.prajwal.todo_app.util.CustomException;
import com.prajwal.todo_app.util.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final TodoRepository repo;

    private final TodoConverter converter;

    @Autowired
    public TodoService(TodoRepository repo, TodoConverter converter) {
        this.repo = repo;
        this.converter = converter;
    }

    private Todo getTodoById(int id) throws CustomException {
        Optional<Todo> todo = repo.findById(id);
        if (todo.isPresent()) {
            return todo.get();

        }
        throw CustomException.notFound("Todo With Id " + id + " Not Found");
    }

    public List<TodoDTO> getAllTodos() {
        List<Todo> todos = repo.findAll();
        return todos.stream().map(converter::converTodoToTodoDTO).collect(Collectors.toList());
    }

    public TodoDTO createTodo(NewTodoDTO newTodoDTO) {
        Todo todo = new Todo();

        todo.setTask(newTodoDTO.getTask());
        todo.setDescription(newTodoDTO.getDescription());
        todo = repo.save(todo);

        return converter.converTodoToTodoDTO(todo);
    }

    public TodoDTO getTodoDTOById(int id) throws CustomException {
        Todo todo = getTodoById(id);
        return converter.converTodoToTodoDTO(todo);
    }

    public TodoDTO updateStatus(int id, TaskStatus status) throws CustomException {
        Todo todo = getTodoById(id);
        todo.setStatus(status);
        repo.save(todo);
        return converter.converTodoToTodoDTO(todo);
    }

    public List<TodoDTO> fetchByStatus(TaskStatus status) {
        return repo.findByStatus(status).stream().map(converter::converTodoToTodoDTO).collect(Collectors.toList());
    }

    public void deleteTodo(int id) throws CustomException {
        Todo todo = getTodoById(id);
        repo.delete(todo);
    }

    public TodoDTO updateTodo(UpdateTodoDTO todo) throws CustomException {
        Todo oldTodo = getTodoById(todo.getId());
        oldTodo.setTask(todo.getTask());
        oldTodo.setDescription(todo.getDescription());
        Todo newTodo = repo.save(oldTodo);
        return converter.converTodoToTodoDTO(newTodo);
    }
}
