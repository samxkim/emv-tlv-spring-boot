package com.omni.webapp.controller;

import com.omni.webapp.models.UserRestModelRequest;
import com.omni.webapp.models.UserRestModelResponse;
import com.omni.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getUser() {
        return "Test";
    }

    @PostMapping
    public UserRestModelResponse createUser (UserRestModelRequest userDetails) {
        return null;
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
