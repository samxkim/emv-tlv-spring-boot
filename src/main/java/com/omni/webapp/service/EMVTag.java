package com.omni.webapp.service;

import com.omni.webapp.models.Tag;

import java.util.Optional;

public interface EMVTag {
    Optional<Tag> getEMVTag(String value);
    Tag getEMVTagByKeyword(String value);
}
