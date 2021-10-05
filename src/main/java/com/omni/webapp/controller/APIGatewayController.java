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

    private final EMVTag emvTag;

    @Autowired
    public APIGatewayController(EMVTag emvTag) {
        this.emvTag = emvTag;
    }

    @RequestMapping(path = "/emvtagsearch/{emvtag}", produces = "application/json")
    public ResponseEntity<TagResponse> getEMVTag(@PathVariable String emvtag) throws TagNotFoundException {
        Optional<Tag> tag = emvTag.getEMVTag(emvtag);
        TagResponse tagResponse = new TagResponse();
        if (tag.isPresent()) {
            tagResponse.setName(tag.get().getName());
            tagResponse.setDescription(tag.get().getDescription());
            tagResponse.setUpdate_date(tag.get().getUpdate_date());
        }

        return tag.map(result -> new ResponseEntity<>(tagResponse, HttpStatus.FOUND))
        .orElseThrow(TagNotFoundException::new);
    }

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
}
