package com.omni.webapp.service;

import com.omni.webapp.models.Tag;
import com.omni.webapp.models.TagRepository;
import javassist.bytecode.stackmap.TypeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EMVTagImpl implements EMVTag {

    @Autowired
    TagRepository tagRepository;

    private final Logger logger = LoggerFactory.getLogger(TypeData.ClassName.class);

    @Override
    public Optional<Tag> getEMVTag(String value) {
        // store string value
        // 9F01
        /// TODO: 10/1/2021 fix up
        try {
            Optional<Tag> returnedTag = Optional.ofNullable(Optional.of(tagRepository.findByName(value)).orElseThrow(
                    // anonymous class?
                    () -> new ResourceNotFoundException(String.format("EMVTag %s not found.", value))));
            System.out.println(returnedTag);
        } catch (NullPointerException e) {
            System.out.println("EMVTag %s not found\n");
        }
        return Optional.empty();
    }

    @Override
    public Optional<Tag> getEMVTagByKeyword(String value) {
        /// TODO: 10/1/2021 make it so it returns a list of entries instead of just one 
        Optional<Tag> returnedTag = Optional.empty();
        try {
            returnedTag = Optional.ofNullable(Optional.of(tagRepository.findByDescriptionContainingIgnoreCase(value)).orElseThrow(
                    // anonymous class?
                    () -> new ResourceNotFoundException(String.format("Description %s not found.", value))));
            System.out.println(returnedTag);
        } catch (NullPointerException e) {
            System.out.println("Tag description %s not found\n");
        }
        return returnedTag;
    }
}
