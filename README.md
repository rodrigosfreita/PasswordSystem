Pensou por dois segundos


# Password System – Detailed Documentation

This document **meticulously** explains every part of the code in this Spring Boot project, detailing the purpose of each file, class, and code snippet.

---

## Table of Contents

1. [Overview](#overview)
2. [Folder Structure](#folder-structure)
3. [pom.xml (Dependency Manager)](#pomxml-dependency-manager)
4. [Configuration & Main Class](#configuration--main-class)
5. [application.properties](#applicationproperties)
6. [`dto` Package (Data Transfer Objects)](#dto-package-data-transfer-objects)

   * GenerateRequest.java
   * GenerateResponse.java
   * ValidateRequest.java
   * ValidateResponse.java
7. [`util` Package](#util-package)

   * PasswordPolicy.java
8. [`service` Package](#service-package)

   * PasswordGeneratorService.java
   * PasswordValidatorService.java
9. [`controller` Package](#controller-package)

   * PasswordController.java
10. [Execution Flow](#execution-flow)
11. [How to Run & Test](#how-to-run--test)

---

## Overview

The **Password System** is a RESTful API providing two main services:

1. **Random Password Generation**
2. **Password Validation** based on security rules

The project uses **Java 17**, **Spring Boot**, and **Bean Validation**.

---

## Folder Structure

```
password-system/
├── pom.xml
├── README.md
└── src
    └── main
        ├── java
        │   └── com.example.password
        │       ├── PasswordSystemApplication.java
        │       ├── controller/PasswordController.java
        │       ├── dto/GenerateRequest.java
        │       ├── dto/GenerateResponse.java
        │       ├── dto/ValidateRequest.java
        │       ├── dto/ValidateResponse.java
        │       ├── service/PasswordGeneratorService.java
        │       ├── service/PasswordValidatorService.java
        │       └── util/PasswordPolicy.java
        └── resources
            └── application.properties
```

---

## pom.xml (Dependency Manager)

* **spring-boot-starter-web**: includes Spring MVC, Jackson, and embedded Tomcat for REST APIs.
* **spring-boot-starter-validation**: enables annotation-based validation (`@Min`, `@NotBlank`, etc.).

---

## Configuration & Main Class

### application.properties

```properties
server.port=8080
```

Sets the server port.

### PasswordSystemApplication.java

```java
@SpringBootApplication
public class PasswordSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(PasswordSystemApplication.class, args);
    }
}
```

* **@SpringBootApplication**: activates auto-configuration, component scan, and bean definitions.
* **main()**: initializes the Spring context and starts the server.

---

## dto Package (Data Transfer Objects)

Objects for JSON data exchange.

### GenerateRequest.java

```java
@Min(4) @Max(64)
private int length;
private boolean useUppercase;
private boolean useDigits;
private boolean useSymbols;
```

* Parameters for password generation; validations enforce a length between 4 and 64.

### GenerateResponse.java

```java
private String password;
```

* Returns the generated password.

### ValidateRequest.java

```java
@NotBlank
private String password;
```

* Receives the password to be validated; does not allow blank values.

### ValidateResponse.java

```java
private boolean valid;
private List<String> errors;
```

* `valid`: indicates success/failure.
* `errors`: list of policy violations.

---

## util Package

### PasswordPolicy.java

```java
public class PasswordPolicy {
    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 32;
    public static final boolean REQUIRE_UPPER = true;
    public static final boolean REQUIRE_LOWER = true;
    public static final boolean REQUIRE_DIGIT = true;
    public static final boolean REQUIRE_SYMBOL = true;
}
```

* Central validation rules.

---

## service Package

### PasswordGeneratorService.java

```java
@Service
public class PasswordGeneratorService {
    // Generates a password using SecureRandom
}
```

### PasswordValidatorService.java

```java
@Service
public class PasswordValidatorService {
    // Validates password according to PasswordPolicy
}
```

---

## controller Package

### PasswordController.java

```java
@RestController
@RequestMapping("/api/password")
@Validated
public class PasswordController {
    @PostMapping("/generate")
    public GenerateResponse generate(@Valid @RequestBody GenerateRequest request) { ... }

    @PostMapping("/validate")
    public ValidateResponse validate(@Valid @RequestBody ValidateRequest request) { ... }
}
```

* REST endpoints for generation and validation.

---

## Execution Flow

1. Client sends JSON to `/api/password/generate` or `/api/password/validate`.
2. Spring deserializes and validates the DTO.
3. The appropriate service executes the logic and returns a response.

---

## How to Run & Test

1. **IntelliJ**: open the project (pom.xml) and run `PasswordSystemApplication`.
2. **Command Line**:

   ```bash
   mvn spring-boot:run
   ```
3. **Examples**:

   * Generate:

     ```bash
     curl -X POST http://localhost:8080/api/password/generate \
     -H "Content-Type: application/json" \
     -d '{"length":12,"useUppercase":true,"useDigits":true,"useSymbols":true}'
     ```
   * Validate:

     ```bash
     curl -X POST http://localhost:8080/api/password/validate \
     -H "Content-Type: application/json" \
     -d '{"password":"MyP@ssw0rd"}'
     ```
