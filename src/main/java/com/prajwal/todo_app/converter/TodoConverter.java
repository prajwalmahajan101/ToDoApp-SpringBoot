package com.prajwal.todo_app.converter;

import com.prajwal.todo_app.dto.TodoDTO;
import com.prajwal.todo_app.model.Todo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public TodoConverter(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

    public TodoDTO converTodoToTodoDTO(Todo todo){
        return modelMapper.map(todo,TodoDTO.class);
    }

    public  Todo converTodoDTOToTodo(TodoDTO todoDTO){
        return modelMapper.map(todoDTO,Todo.class);
    }

}
