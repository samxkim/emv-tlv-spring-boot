package com.omni.webapp.service;

import java.util.List;

public interface EMVTag<T> {
    List<String> getEMVTag(T value);
    String getKeyword(T value);
}
