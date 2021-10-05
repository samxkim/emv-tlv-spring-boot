package com.omni.webapp.controller;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.omni.webapp.models.*;
import com.omni.webapp.service.EMVTag;
import javassist.NotFoundException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

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
                //.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        .orElseThrow(TagNotFoundException::new);
    }

//    // REST API ERROR RESPONSE RESTCONTROLLERADVICE DOESNT WORK I TRIED WTF ONLY WORKS IN SAME FILE
//    // NVM DSAIOJKLDMALFDLF
//    @ExceptionHandler(value = TagNotFoundException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ResponseEntity<APIErrorResponse> resolveException(TagNotFoundException ex, HttpServletRequest request){
//        APIErrorResponse apiErrorResponse = new APIErrorResponse(HttpStatus.BAD_REQUEST,
//                "404", "Invalid request input", "Please enter a valid EMV Tag.");
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
//    }

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

//    @ExceptionHandler(RuntimeException.class)
//    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
//        return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
