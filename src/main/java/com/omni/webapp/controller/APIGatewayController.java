package com.omni.webapp.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.omni.webapp.models.*;
import com.omni.webapp.service.EMVTag;
import com.omni.webapp.service.TLVDecoder;
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
    private final TLVDecoder tlvDecoder;

    @Autowired
    public APIGatewayController(EMVTag emvTag, TLVDecoder tlvDecoder) {
        this.emvTag = emvTag;
        this.tlvDecoder = tlvDecoder;
    }

    @RequestMapping(path = "/emvtagsearch/{emvtag}", produces = "application/json")
    public ResponseEntity<TagResponse> getEMVTag(@PathVariable String emvtag) throws TagNotFoundException {
        logger.info("Inputted variable: {}", emvtag);

        Optional<Tag> tag = emvTag.getEMVTag(emvtag);
        logger.info(String.valueOf(tag));

        TagResponse tagResponse = new TagResponse();
        if (tag.isPresent()) {
            tagResponse.setName(tag.get().getName());
            tagResponse.setDescription(tag.get().getDescription());
            tagResponse.setUpdate_date(tag.get().getUpdate_date());
        }

        return tag.map(result -> new ResponseEntity<>(tagResponse, HttpStatus.FOUND))
        .orElseThrow(TagNotFoundException::new);
    }

    @RequestMapping(path = "/tlvdecoder/{tlv}", produces = "application/json")
    public ResponseEntity<List<List<String>>> getTLVData(@PathVariable String tlv) throws InvalidTLVException {
        logger.info("Inputted TLV: {}", tlv);
        try{
            List<List<String>> tlvList = tlvDecoder.decodeTLVData(tlv);
            logger.info(String.valueOf(tlvList));
            return new ResponseEntity<>(tlvList, HttpStatus.FOUND);
        } catch (Exception e) {
            throw new InvalidTLVException();
        }
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
