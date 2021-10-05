package com.omni.webapp.service;

import com.omni.webapp.models.Tag;

import java.util.List;
import java.util.Optional;

public interface EMVTag {
    Optional<Tag> getEMVTag(String value);
    Optional<List<Tag>> getEMVTagByKeyword(String value);
}
