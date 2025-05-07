package com.example.password.service;

import com.example.password.dto.GenerateRequest;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PasswordGeneratorService {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_+=";
    private final SecureRandom random = new SecureRandom();

    public String generate(GenerateRequest request) {
        StringBuilder alphabet = new StringBuilder(LOWER);
        if (request.isUseUppercase()) alphabet.append(UPPER);
        if (request.isUseDigits()) alphabet.append(DIGITS);
        if (request.isUseSymbols()) alphabet.append(SYMBOLS);

        String chars = alphabet.toString();
        StringBuilder password = new StringBuilder(request.getLength());
        for (int i = 0; i < request.getLength(); i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }
}
