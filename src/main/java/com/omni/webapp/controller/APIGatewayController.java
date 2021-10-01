package com.omni.webapp.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
        //Tag t1 = new Tag("Name", "Description", "createdby", true);
        List<Tag> tags = new ArrayList<>();

        // Iterates through each static constant in the specified class
        for (Field f : InitialEMVTags.class.getDeclaredFields()) {
            f.setAccessible(true);
            //String name = f.getName();
            //System.out.println(name);
            try {
                Tag currentTag = (Tag) f.get(null);
                if (!tagRepository.findByNameNotNull(currentTag.getName())) {
                    //System.out.println("FUCK");
                    tags.add(currentTag);
                }
//                else if (!tagRepository.findByNameNotNull(currentTag.getName())) {
//                    tags.add(currentTag);
//                }
                //System.out.println(value.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(tags);
        tagRepository.saveAll(tags);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
