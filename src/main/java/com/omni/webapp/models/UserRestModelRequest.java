package com.omni.webapp.models;


import javax.validation.constraints.*;

public class UserRestModelRequest {
    @NotEmpty(message = "Username is required.")
    @Size(min = 2, max = 32, message = "Length must be between 2 and 32 characters.")
    private String userName;

    @NotEmpty(message = "Email is required.")
    @Email(message = "Email address is invalid", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @NotEmpty(message = "Password is required.")
    private String password;

    @NotEmpty(message = "Company name is required.")
    private String companyName;

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPassword() {
        return password;
    }
}
