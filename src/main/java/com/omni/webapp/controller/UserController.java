package com.omni.webapp.controller;

import com.omni.webapp.models.UserEntity;
import com.omni.webapp.models.UserRepository;
import com.omni.webapp.models.UserRestModelResponseDto;
import com.omni.webapp.service.DBUserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("users")
public class UserController {

    private final DBUserDetailsImpl dbUserDetails;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserController(DBUserDetailsImpl dbUserDetails,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          UserRepository userRepository) {
        this.dbUserDetails = dbUserDetails;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity<UserRestModelResponseDto> createUser(@RequestParam(name = "username") String username,
                                                              @RequestParam(name = "password") String password,
                                                              @RequestParam(name = "email") String email,
                                                              @RequestParam(name = "company_name") String companyName) throws UserNotFoundException {
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        // duplicated entries, password length, duplicate email, etc
        // todo: deal with exceptions
        UserEntity user = new UserEntity(username, email, companyName, encodedPassword, true, "ROLE_USER");
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserAlreadyExistsException(String.format("Username: %s already exists", user.getUserName()));
        }
        UserRestModelResponseDto responseDto = new UserRestModelResponseDto(username, email, companyName);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/login", produces = "application/json", method = RequestMethod.GET)
    public UserRestModelResponseDto getUser(@RequestParam(name = "username") String username,
                                            @RequestParam(name = "password") String password) throws UserNotFoundException{
        UserDetails user = dbUserDetails.loadUserByUsername(username);
        Boolean doesPasswordMatch = bCryptPasswordEncoder.matches(password, user.getPassword());
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

    public static boolean passwordValidLength(String password) {
        Pattern pattern = Pattern.compile("^[a-zA-Z@#$%^&+=](?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
