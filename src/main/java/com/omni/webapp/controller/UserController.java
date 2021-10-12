package com.omni.webapp.controller;

import com.omni.webapp.models.*;
import com.omni.webapp.service.DBUserDetailsImpl;
import com.omni.webapp.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.omni.webapp.utils.PasswordUtils.passwordValidLength;

@RestController
@Validated
@RequestMapping("users")
public class UserController {

    private final DBUserDetailsImpl dbUserDetails;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserController(DBUserDetailsImpl dbUserDetails,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          UserRepository userRepository,
                          JwtUtils jwtUtils) {
        this.dbUserDetails = dbUserDetails;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity<UserRestModelResponse> createUser(@Valid @RequestBody UserRestModelRequest userRestModelRequestDto) throws UserAlreadyExistsException, UserPasswordException {
        if (!passwordValidLength(userRestModelRequestDto.getPassword())) {
            throw new UserPasswordException("Password is not valid.");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(userRestModelRequestDto.getPassword());
        UserEntity user = new UserEntity(userRestModelRequestDto.getUserName(), userRestModelRequestDto.getEmail(),
                userRestModelRequestDto.getCompanyName(), encodedPassword, true, "ROLE_USER");
        try {
            if (userRepository.existsByUserName(user.getUserName())) {
                throw new UserAlreadyExistsException("Username already exists");
            }
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException("Email already exists");
        }
        UserRestModelResponse responseDto = new UserRestModelResponse(userRestModelRequestDto.getUserName(), userRestModelRequestDto.getEmail(), userRestModelRequestDto.getCompanyName());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/login", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<JwtResponse> getUser(@RequestParam(name = "username") String username,
                                               @RequestParam(name = "password") String password) throws UserNotFoundException{
        try {
            UserDetails user = dbUserDetails.loadUserByUsername(username);
            boolean doesPasswordMatch = bCryptPasswordEncoder.matches(password, user.getPassword());
            if (!doesPasswordMatch) {
                throw new UserPasswordException("Incorrect User/Password.");
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            final String token = jwtUtils.generateToken(user);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (AuthenticationException e) {
            System.out.println("Unable");
        }
        return null;
    }

    @PutMapping(path = "/update", produces = "application/json")
    public String updateUser() {
        // todo:
        // checks on update user
        // checks for existing data
        // exception handling
        // permission roles (ROLE_USER, ROLE_ADMIN)
        return "Rest";
    }

    @DeleteMapping
    public String deleteUser() {
        // todo:
        // checks on delete user
        // checks for existing data
        // exception handling
        // permission roles (ROLE_USER, ROLE_ADMIN)
        return "Rest";
    }
}
