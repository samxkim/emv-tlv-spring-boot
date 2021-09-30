package com.omni.webapp.service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import com.omni.webapp.FileToTest;
import com.omni.webapp.models.TagRepository;
import com.omni.webapp.models.UserRepository;
import com.payneteasy.tlv.*;
import javassist.bytecode.stackmap.TypeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EMVTagImpl implements EMVTag<String>{

    @Autowired
    TagRepository tagRepository;

    private final Logger logger = LoggerFactory.getLogger(TypeData.ClassName.class);

    @Override
    public List<String> getEMVTag(String value) {
        // store string value
        // 9F01

        // send to repository
        tagRepository.findByName(value);

        // try to search

        // get returned result

        // list of strings of info

        // return
        return null;
    }

    @Override
    public String getKeyword(String value) {
        return null;
    }
}
