package com.omni.webapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public String getUser() {
        return "Test";
    }

    @PostMapping
    public UserRest createUser (@RequestMapping UserDetailRequestModel userDetails) {
        return "Test";
    }

    @PutMapping
    public String updateUser() {
        return "Rest";
    }
    
    @DeleteMapping
    public String deleteUser() {
        return "Rest";
    }
}
