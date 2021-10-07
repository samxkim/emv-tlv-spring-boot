package com.omni.webapp.controller;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
