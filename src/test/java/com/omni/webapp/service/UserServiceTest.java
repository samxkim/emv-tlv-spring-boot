package com.omni.webapp.service;

import com.omni.webapp.models.TagRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase
class UserServiceTest {
    @Autowired
    private EMVTag emvTag;

    @Autowired
    private TagRepository tagRepository;

    @BeforeEach
    public void init() {
    }

    @AfterEach
    public void destroy() {
        tagRepository.deleteAll();
    }
}