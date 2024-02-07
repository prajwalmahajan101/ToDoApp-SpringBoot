package com.prajwal.todo_app.util;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {
    private HttpStatus statusCode = HttpStatus.BAD_REQUEST;


    public CustomException() {
        super("Some Error Occurred");
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(HttpStatus statusCode) {
        super("Some Error Occurred");
        this.statusCode = statusCode;
    }

    public CustomException( HttpStatus statusCode,String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }


    public static CustomException notFound(String message){
        return new CustomException(HttpStatus.NOT_FOUND,message);
    }

    public static CustomException notFound(){
        return new CustomException(HttpStatus.NOT_FOUND,"Not Found");
    }

    public static CustomException badRequest(String message){
        return new CustomException(HttpStatus.BAD_REQUEST,message);
    }

    public static CustomException badRequest(){
        return new CustomException(HttpStatus.BAD_REQUEST,"Bad Request");
    }

    public static CustomException invalidInput(String message){
        return new CustomException(HttpStatus.UNPROCESSABLE_ENTITY,message);
    }

    public static CustomException invalidInput(){
        return new CustomException(HttpStatus.NOT_FOUND,"Invalid Input");
    }



}
