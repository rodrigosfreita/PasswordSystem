package com.example.password.dto;

public class GenerateResponse {
    private String password;

    public GenerateResponse() { }

    public GenerateResponse(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
