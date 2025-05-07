package com.example.password.service;

import com.example.password.util.PasswordPolicy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordValidatorService {

    public List<String> validate(String password) {
        List<String> errors = new ArrayList<>();

        if (password.length() < PasswordPolicy.MIN_LENGTH) {
            errors.add("Tamanho mínimo: " + PasswordPolicy.MIN_LENGTH);
        }
        if (password.length() > PasswordPolicy.MAX_LENGTH) {
            errors.add("Tamanho máximo: " + PasswordPolicy.MAX_LENGTH);
        }
        if (PasswordPolicy.REQUIRE_UPPER && !password.matches(".*[A-Z].*")) {
            errors.add("Deve conter ao menos uma letra maiúscula");
        }
        if (PasswordPolicy.REQUIRE_LOWER && !password.matches(".*[a-z].*")) {
            errors.add("Deve conter ao menos uma letra minúscula");
        }
        if (PasswordPolicy.REQUIRE_DIGIT && !password.matches(".*\d.*")) {
            errors.add("Deve conter ao menos um dígito");
        }
        if (PasswordPolicy.REQUIRE_SYMBOL && !password.matches(".*[^a-zA-Z0-9].*")) {
            errors.add("Deve conter ao menos um símbolo especial");
        }

        return errors;
    }
}
