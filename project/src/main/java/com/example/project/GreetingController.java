package com.example.demo.project;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class GreetingController {

    // Endpoint 1:
    @GetMapping("/hello")
    public String sayHello() {
        return "Pershendetje nga Spring Boot!";
    }

    // Endpoint 2
    @PostMapping("/greet")
    public String greetByLanguage(@RequestParam String language) {
        return switch (language.toLowerCase()) {
            case "en" -> "Hello!";
            case "sq" -> "Pershendetje!";
            case "fr" -> "Bonjour!";
            default -> "Gjuha nuk njihet.";
        };
    }
}
