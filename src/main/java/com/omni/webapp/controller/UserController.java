package com.omni.webapp.controller;

import com.omni.webapp.models.UserRestModelRequestDto;
import com.omni.webapp.models.UserRestModelResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @PostMapping(path = "/register", produces = "application/json")
    public UserRestModelResponseDto createUser(@RequestParam(name = "username") String username,
                                               @RequestParam(name = "password") String password,
                                               @RequestParam(name = "email") String email,
                                               @RequestParam(name = "company_name") String companyName) throws UserNotFoundException {
        return null;
    }

    @PostMapping
    public String getUser() {
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
