package com.omni.webapp.controller;

public class UserPasswordException extends RuntimeException {
    public UserPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
