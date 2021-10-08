package com.omni.webapp.controller;

import com.omni.webapp.models.Tag;
import com.omni.webapp.models.TagRepository;
import com.omni.webapp.models.UserEntity;
import com.omni.webapp.models.UserRepository;
import com.omni.webapp.service.DBUserDetailsImpl;
import com.omni.webapp.service.EMVTag;
import com.omni.webapp.service.TLVDecoder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

        //given(userRepository.save(user))
                //.willReturn(user);

        this.mockMvc.perform(post("/users/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().string(responseBody));
    }

    @Test
    public void registerInvalidUsernameInput() throws Exception {
        //{
        //    "status": "BAD_REQUEST",
        //    "errorCode": "400",
        //    "message": "Username already exists",
        //    "detail": "Please enter differently."
        //}
        fail();
    }

    @Test
    public void registerInvalidEmailInput() throws Exception {
        //{
        //    "status": "BAD_REQUEST",
        //    "errorCode": "400",
        //    "message": "Email already exists",
        //    "detail": "Please enter differently."
        //}
        fail();
    }

    @Test
    public void registerInvalidPasswordInput() throws Exception {
        //{
        //    "status": "BAD_REQUEST",
        //    "errorCode": "400",
        //    "message": "Password is not valid.",
        //    "detail": "Password must have at least eight characters, one special character, one uppercase letter, one lowercase letter and one number."
        //}
        fail();
    }

}