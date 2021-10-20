package com.omni.webapp.service;

import com.omni.webapp.controller.TagNotFoundException;
import com.omni.webapp.models.Tag;
import com.omni.webapp.models.TagRepository;
import javassist.bytecode.stackmap.TypeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EMVTagImpl implements EMVTag {

    final TagRepository tagRepository;

    private final Logger logger = LoggerFactory.getLogger(TypeData.ClassName.class);

    public EMVTagImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag getEMVTag(String value) {
        try {
            //return Optional.ofNullable(Optional.of(tagRepository.findByName(value)).orElseThrow(
            //        () -> new ResourceNotFoundException(String.format("EMVTag %s not found.", value))));
            return tagRepository.findByName(value);
        } catch (NullPointerException e) {
            // change to default exception
            throw new TagNotFoundException("EMV Tag not found.");
        }
        //return Optional.empty();
    }

    @Override
    public List<Tag> getEMVTagByKeyword(String value) {
        try {
            //returnedTag = Optional.ofNullable(Optional.of(tagRepository.findAllByDescriptionContainingIgnoreCaseOrderById(value)).orElseThrow(
                    //() -> new ResourceNotFoundException(String.format("Description %s not found.", value))));
            //System.out.println("HEY" + returnedTag);
            return tagRepository.findAllByDescriptionContainingIgnoreCaseOrderById(value);
        } catch (NullPointerException e) {
            // change to default exception
            throw new TagNotFoundException("Tag(s) with keyword not found.");
        }
    }
}
