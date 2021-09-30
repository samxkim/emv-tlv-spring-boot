package com.omni.webapp.controller;

import com.omni.webapp.models.UserRestModelRequestDto;
import com.omni.webapp.models.UserRestModelResponseDto;
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
    public UserRestModelResponseDto createUser (UserRestModelRequestDto userDetails) {
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
