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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository repo;

    private final TodoConverter converter;

    @Autowired
    public TodoServiceImpl(TodoRepository repo, TodoConverter converter) {
        this.repo = repo;
        this.converter = converter;
    }

    private Todo getById(int id) throws CustomException {
        Optional<Todo> todo = repo.findById(id);
        if (todo.isPresent()) {
            return todo.get();

        }
        throw CustomException.notFound("Todo With Id " + id + " Not Found");
    }

    private Sort sortByIdAsc() {
        return Sort.by(Sort.Direction.ASC, "id");
    }

    @Override
    public List<TodoDTO> getAllTodos() {
        List<Todo> todos = repo.findAll(sortByIdAsc());
        return todos.stream().map(converter::converTodoToTodoDTO).collect(Collectors.toList());
    }

    @Override
    public TodoDTO createTodo(NewTodoDTO newTodoDTO) {
        Todo todo = new Todo();

        todo.setTask(newTodoDTO.getTask());
        todo.setDescription(newTodoDTO.getDescription());
        todo = repo.save(todo);

        return converter.converTodoToTodoDTO(todo);
    }

    @Override
    public TodoDTO getTodoById(int id) throws CustomException {
        Todo todo = getById(id);
        return converter.converTodoToTodoDTO(todo);
    }

    @Override
    public TodoDTO updateStatus(int id, TaskStatus status) throws CustomException {
        Todo todo = getById(id);
        todo.setStatus(status);
        repo.save(todo);
        return converter.converTodoToTodoDTO(todo);
    }

    @Override
    public List<TodoDTO> fetchByStatus(TaskStatus status) {
        return repo.findByStatusOrderById(status).stream().map(converter::converTodoToTodoDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteTodo(int id) throws CustomException {
        Todo todo = getById(id);
        repo.delete(todo);
    }

    @Override
    public TodoDTO updateTodo(UpdateTodoDTO todo) throws CustomException {
        Todo oldTodo = getById(todo.getId());
        oldTodo.setTask(todo.getTask());
        oldTodo.setDescription(todo.getDescription());
        Todo newTodo = repo.save(oldTodo);
        return converter.converTodoToTodoDTO(newTodo);
    }
}
