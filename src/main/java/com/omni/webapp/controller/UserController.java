package com.omni.webapp.controller;

import com.omni.webapp.models.UserEntity;
import com.omni.webapp.models.UserRepository;
import com.omni.webapp.models.UserRestModelRequestDto;
import com.omni.webapp.models.UserRestModelResponseDto;
import com.omni.webapp.service.DBUserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static boolean passwordValidLength(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity<UserRestModelResponseDto> createUser(@Valid @RequestBody UserRestModelRequestDto userRestModelRequestDto) throws UserAlreadyExistsException {
//        @RequestParam(name = "username") String username,
//        @RequestParam(name = "password") String password,
//        @RequestParam(name = "email") String email,
//        @RequestParam(name = "company_name") String companyName,
        if (!passwordValidLength(userRestModelRequestDto.getPassword())) {
            throw new UserPasswordException("Password is not valid.");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(userRestModelRequestDto.getPassword());
        // duplicated entries, password length, duplicate email, etc
        // todo: deal with exceptions
        // UserNotFoundException
        UserEntity user = new UserEntity(userRestModelRequestDto.getUserName(), userRestModelRequestDto.getEmail(),
                userRestModelRequestDto.getCompanyName(), encodedPassword, true, "ROLE_USER");
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserAlreadyExistsException(String.format("Username: %s already exists", user.getUserName()));
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
