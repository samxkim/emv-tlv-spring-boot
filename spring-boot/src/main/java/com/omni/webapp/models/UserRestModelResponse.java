package com.omni.webapp.models;

public class UserRestModelResponse {
    private String userName;
    private String email;
    private String companyName;

    public UserRestModelResponse(String userName, String email, String companyName) {
        this.userName = userName;
        this.email = email;
        this.companyName = companyName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
