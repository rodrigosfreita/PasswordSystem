package com.example.password.dto;

import java.util.List;

public class ValidateResponse {
    private boolean valid;
    private List<String> errors;

    public ValidateResponse() { }

    public ValidateResponse(boolean valid, List<String> errors) {
        this.valid = valid;
        this.errors = errors;
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
