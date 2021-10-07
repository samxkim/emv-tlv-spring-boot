package com.omni.webapp.controller;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
