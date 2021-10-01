package com.omni.webapp.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.omni.webapp.models.*;
import com.omni.webapp.service.EMVTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIGatewayController {
    private static final Logger LOG = LoggerFactory.getLogger(APIGatewayController.class);

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private EMVTag emvTag;

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        final String template = "Hello, %s!";
        final AtomicLong counter = new AtomicLong();
        emvTag.getEMVTag("06dsadsa");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
