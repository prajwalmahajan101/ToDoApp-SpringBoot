package com.prajwal.todo_app.advice;

import com.prajwal.todo_app.util.CustomException;
import com.prajwal.todo_app.util.CustomResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomResponse<Map<String,String>> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String,String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err->errorMap.put(err.getField(),err.getDefaultMessage()));
        return new CustomResponse<>(CustomException.badRequest(),errorMap);
    }

    @ExceptionHandler(CustomException.class)
    public CustomResponse<?> handleCustomException(CustomException e){
        return new CustomResponse<>(e);
    }
}
