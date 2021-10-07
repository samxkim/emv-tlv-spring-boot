package com.omni.webapp.controller;

import com.omni.webapp.models.UserEntity;
import com.omni.webapp.models.UserRepository;
import com.omni.webapp.models.UserRestModelRequestDto;
import com.omni.webapp.models.UserRestModelResponseDto;
import com.omni.webapp.service.DBUserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public UserController(DBUserDetailsImpl dbUserDetails,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          UserRepository userRepository) {
        this.dbUserDetails = dbUserDetails;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity<UserRestModelResponseDto> createUser(@Valid @RequestBody UserRestModelRequestDto userRestModelRequestDto) throws UserAlreadyExistsException, UserPasswordException {
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
        UserRestModelResponseDto responseDto = new UserRestModelResponseDto(userRestModelRequestDto.getUserName(), userRestModelRequestDto.getEmail(), userRestModelRequestDto.getCompanyName());
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
}
