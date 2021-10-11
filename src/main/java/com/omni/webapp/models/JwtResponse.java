package com.omni.webapp.models;

public class JwtResponse {
    private String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public JwtResponse() {
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
