package com.omni.webapp.controller;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @RequestMapping(path = "/emvtagsearchdescription", produces = "application/json")
    public ResponseEntity<List<TagResponse>> getEMVByDescription(@RequestParam(name = "description") String description) throws TagNotFoundException {
        logger.info("Inputted variable: {}", description);

        List<TagResponse> tagResponseList = new ArrayList<>();
        Optional<List<Tag>> tag = emvTag.getEMVTagByKeyword(description);
        logger.info(String.valueOf(tag));

        if (tag.isPresent()) {
            List<Tag> tagList = tag.get();
            for (Tag value : tagList) {
                TagResponse tagResponse = new TagResponse();
                tagResponse.setName(value.getName());
                tagResponse.setDescription(value.getDescription());
                tagResponseList.add(tagResponse);
            }
        }

        return tag.map(result -> new ResponseEntity<>(tagResponseList, HttpStatus.FOUND))
                .orElseThrow(TagNotFoundException::new);
    }

    @RequestMapping(path = "/emvtagsearch", produces = "application/json")
    public ResponseEntity<TagResponse> getEMVTag(@RequestParam("id") String id) throws TagNotFoundException {
        logger.info("Inputted variable: {}", id);

        Optional<Tag> tag = emvTag.getEMVTag(id);
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
}
