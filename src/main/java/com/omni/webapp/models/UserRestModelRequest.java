package com.omni.webapp.models;


import javax.validation.constraints.*;

public class UserRestModelRequest {
    @NotEmpty(message = "Username is required.")
    @Size(min = 2, max = 32, message = "Length must be between 2 and 32 characters.")
    private final String userName;

    @NotEmpty(message = "Email is required.")
    @Email(message = "Email address is invalid", flags = Pattern.Flag.CASE_INSENSITIVE)
    private final String email;

    @NotEmpty(message = "Password is required.")
    private final String password;

    @NotEmpty(message = "Company name is required.")
    private final String companyName;

    public UserRestModelRequest(String userName, String email, String password, String companyName) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.companyName = companyName;
    }

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
