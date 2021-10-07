package com.omni.webapp.controller;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
