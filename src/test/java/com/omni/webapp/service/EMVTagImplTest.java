package com.omni.webapp.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EMVTagImplTest {
    EMVTagImpl emvTag = new EMVTagImpl();

    @Test
    void getEMVTag() {
        emvTag.getEMVTag("9F01");
    }

    @Test
    void getKeyword() {
    }
}