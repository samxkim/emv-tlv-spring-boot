package com.omni.webapp.service;

import com.omni.webapp.models.Tag;

import java.util.List;
import java.util.Optional;

public interface EMVTag {
    Tag getEMVTag(String value);
    List<Tag> getEMVTagByKeyword(String value);
}
