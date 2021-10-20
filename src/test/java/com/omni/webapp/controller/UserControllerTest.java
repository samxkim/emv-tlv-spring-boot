package com.omni.webapp.controller;

import com.omni.webapp.models.Tag;
import com.omni.webapp.models.TagRepository;
import com.omni.webapp.models.UserEntity;
import com.omni.webapp.models.UserRepository;
import com.omni.webapp.service.DBUserDetailsImpl;
import com.omni.webapp.service.EMVTag;
import com.omni.webapp.service.TLVDecoder;
import com.omni.webapp.utils.PasswordUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static com.omni.webapp.utils.PasswordUtils.passwordValidLength;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private DBUserDetailsImpl dbUserDetails;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(springSecurity())
                .apply(sharedHttpSession())
                .build();
    }

    @AfterEach
    public void destroy() {
        //userRepository.deleteAll();
    }

    @Test
    public void registerValidInput() throws Exception {
        String requestBody = "{\n" +
                "    \"userName\": \"dsadasdsaddsadasdasdsadsadas\",\n" +
                "    \"email\": \"hedsadasy@gmaishdsadsagfhgfl.com\",\n" +
                "    \"password\": \"1peepe@epeepeeP\",\n" +
                "    \"companyName\": \"dsa\"\n" +
                "}";
        String responseBody = "{\"userName\":\"dsadasdsaddsadasdasdsadsadas\",\"email\":\"hedsadasy@gmaishdsadsagfhgfl.com\",\"companyName\":\"dsa\"}";
        UserEntity user = new UserEntity("dsadasdsadasdsadsadas", "hedsadasy@gmaishdsadsagfhgfl.com",
                "dsa", true, "ROLE_USER");

        given(userRepository.save(user))
                .willReturn(user);

        this.mockMvc.perform(post("/api/v1/users/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().string(responseBody));
    }

    @Test
    public void registerInvalidUsernameInput() throws Exception {
        String requestBody = "{\n" +
                "    \"userName\": \"dsadasdsadasdsadsadas\",\n" +
                "    \"email\": \"hedsadassy@gmaishdsadsagfhgfl.com\",\n" +
                "    \"password\": \"1peepe@epeepeeP\",\n" +
                "    \"companyName\": \"dsa\"\n" +
                "}";
        String responseBody = "{\"status\":\"BAD_REQUEST\",\"errorCode\":\"400\",\"message\":\"Username already exists\",\"detail\":\"Please enter differently.\"}";
        UserEntity storedUser = new UserEntity("dsadasdsadasdsadsadas", "hedsadasy@gmaishdsadsagfhgfl.com",
                "dsa", "peepee", true, "ROLE_USER");
        userRepository.save(storedUser);
        UserEntity user = new UserEntity("dsadasdsadasdsadsadas", "hedsadasy@gmaishdsadsagfhgfl.com",
                "dsa", "peepee", true, "ROLE_USER");

        given(userRepository.existsByUserName(user.getUserName()))
                .willReturn(true);

        this.mockMvc.perform(post("/api/v1/users/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(responseBody));
    }

    @Test
    public void registerInvalidEmailInput() throws Exception {
        String requestBody = "{\n" +
                "    \"userName\": \"dsadasdsadasdsadsadasdsadsadas\",\n" +
                "    \"email\": \"hedsadasy@gmaishdsadsagfhgfl.com\",\n" +
                "    \"password\": \"1peepe@epeepeeP\",\n" +
                "    \"companyName\": \"dsa\"\n" +
                "}";
        String responseBody = "{\"status\":\"BAD_REQUEST\",\"errorCode\":\"400\",\"message\":\"Email already exists\",\"detail\":\"Please enter differently.\"}";
        doThrow(new UserAlreadyExistsException("Email already exists")).when(userRepository).save(any(UserEntity.class));

        this.mockMvc.perform(post("/api/v1/users/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(responseBody));
    }
}