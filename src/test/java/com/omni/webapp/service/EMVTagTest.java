package com.omni.webapp.service;

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
        tagRepository.deleteAll();
    }

    @Test
    public void checkForPresentTagValue() {
        Optional<Tag> returnedTag = emvTag.getEMVTag("5f50");
        assertFalse(returnedTag.isPresent());
    }

    @Test
    public void checkForInvalidTagValue() {
        Optional<Tag> returnedTag = emvTag.getEMVTag("dsadsadasdsa");
        assertFalse(returnedTag.isPresent());
    }

    @Test
    public void checkForValidKeyword() {
        Optional<List<Tag>> returnedTagList = emvTag.getEMVTagByKeyword("Country Code");
        Integer sizeOfList = returnedTagList.map(List::size).orElse(0);
        assertEquals(5, sizeOfList);
    }

    @Test
    public void checkForInvalidKeyword() {
        Optional<List<Tag>> returnedTagList = emvTag.getEMVTagByKeyword("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Integer sizeOfList = returnedTagList.map(List::size).orElse(0);
        assertEquals(0, sizeOfList);
    }
}