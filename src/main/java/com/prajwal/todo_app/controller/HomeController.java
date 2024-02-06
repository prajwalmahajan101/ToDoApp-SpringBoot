package com.prajwal.todo_app.controller;

import com.prajwal.todo_app.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Response<String> home() {
        return new Response<>("API Working Properly", null);
    }

}
