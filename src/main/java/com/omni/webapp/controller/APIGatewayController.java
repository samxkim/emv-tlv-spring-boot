package com.omni.webapp.controller;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.omni.webapp.models.*;
import com.omni.webapp.service.EMVTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class APIGatewayController {
    private static final Logger logger = LoggerFactory.getLogger(APIGatewayController.class);

    private final TagRepository tagRepository;
    private final EMVTag emvTag;

    @Autowired
    public APIGatewayController(TagRepository tagRepository, EMVTag emvTag) {
        this.tagRepository = tagRepository;
        this.emvTag = emvTag;
    }

    @RequestMapping(value = "/emvtagsearch", method = RequestMethod.POST)

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        final String template = "Hello, %s!";
        final AtomicLong counter = new AtomicLong();
        Optional<Tag> tag = emvTag.getEMVTag("5f50");
        emvTag.getEMVTag("06dsadsa");
        emvTag.getEMVTagByKeyword("OID");
        emvTag.getEMVTagByKeyword("DSAIODMASLKDKASLDMAS");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
