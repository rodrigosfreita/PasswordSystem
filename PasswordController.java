package com.example.password.controller;

import com.example.password.dto.*;
import com.example.password.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/password")
@Validated
public class PasswordController {

    private final PasswordGeneratorService generator;
    private final PasswordValidatorService validator;

    public PasswordController(PasswordGeneratorService generator, PasswordValidatorService validator) {
        this.generator = generator;
        this.validator = validator;
    }

    @PostMapping("/generate")
    public ResponseEntity<GenerateResponse> generate(@Valid @RequestBody GenerateRequest request) {
        String password = generator.generate(request);
        return ResponseEntity.ok(new GenerateResponse(password));
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateResponse> validate(@Valid @RequestBody ValidateRequest request) {
        List<String> errors = validator.validate(request.getPassword());
        boolean valid = errors.isEmpty();
        return ResponseEntity.ok(new ValidateResponse(valid, errors));
    }
}
