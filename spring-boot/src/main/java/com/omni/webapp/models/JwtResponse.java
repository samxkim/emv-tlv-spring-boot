package com.omni.webapp.models;

import java.util.Date;

public class JwtResponse {
    private String jwtToken;
    private Date dateOfExpiry;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public JwtResponse() {
    }

    public JwtResponse(String token, Date date) {
        this.jwtToken = token;
        this.dateOfExpiry = date;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public Date getDateOfExpiry() {
        return dateOfExpiry;
    }
}
