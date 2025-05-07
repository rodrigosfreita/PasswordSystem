package com.example.password.dto;

import javax.validation.constraints.NotBlank;

public class ValidateRequest {

    @NotBlank
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
