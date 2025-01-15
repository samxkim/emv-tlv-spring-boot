package com.omni.webapp.service;

import com.omni.webapp.controller.TagNotFoundException;
import com.omni.webapp.models.Tag;
import com.omni.webapp.models.TagRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
class EMVTagTest {

    @Autowired
    private EMVTag emvTag;

    @Autowired
    private TagRepository tagRepository;

    @BeforeEach
    public void init() {
    }

    @AfterEach
    public void destroy() {
        //tagRepository.deleteAll();
    }

    @Test
    public void checkForPresentTagValue() {
        Tag returnedTag = emvTag.getEMVTag("5f50");
        assertEquals(returnedTag.getName(), "5f50");
    }

    @Test
    public void checkForInvalidTagValue() {
        Tag returnedTag = emvTag.getEMVTag("dsadsadasdsa");
        assertNull(returnedTag);
    }

    @Test
    public void checkForValidKeyword() {
        List<Tag> returnedTagList = emvTag.getEMVTagByKeyword("Country Code");
        assertEquals(5, returnedTagList.size());
    }

    @Test
    public void checkForInvalidKeyword() {
        List<Tag> returnedTagList = emvTag.getEMVTagByKeyword("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        assertEquals(0, returnedTagList.size());
    }
}