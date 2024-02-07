package com.prajwal.todo_app.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class CustomResponse<T> extends ResponseEntity<Response<T>> {


    public CustomResponse(Response<T> body, HttpStatusCode status) {
        super(body, status);
    }

    public CustomResponse(HttpStatus status,String msg,T data){
        this(new Response<>(msg,data),status);
    }
    public CustomResponse(HttpStatus status,String msg){
        this(new Response<>(msg,null),status);
    }
    public CustomResponse(CustomException e){
        this(new Response<>(e),e.getStatusCode());
    }
}
