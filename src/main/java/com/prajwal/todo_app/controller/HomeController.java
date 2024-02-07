package com.prajwal.todo_app.controller;

import com.prajwal.todo_app.util.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public CustomResponse<String> home() {
        return new CustomResponse<>(HttpStatus.OK,"API Working Properly");
    }

}
