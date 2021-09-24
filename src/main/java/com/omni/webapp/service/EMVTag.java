package com.omni.webapp.service;

public interface EMVTag<T> {
    String getEMVTag(T value);
    String getKeyword(T value);
}
