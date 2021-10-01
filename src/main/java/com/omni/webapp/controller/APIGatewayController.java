package com.omni.webapp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.omni.webapp.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIGatewayController {

    @Autowired
    private TagRepository tagRepository;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        insertInitialTags();
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    public void insertInitialTags() {
        // TODO: 10/1/2021  create static tags to iterate upon
        Tag t1 = new Tag("Name", "Description", "createdby", true);
        List<Tag> tags = List.of(t1);
        tagRepository.saveAll(tags);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
