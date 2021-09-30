package com.omni.webapp.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.omni.webapp.models.Greeting;
import com.omni.webapp.models.Tag;
import com.omni.webapp.models.TagRepository;
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
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
