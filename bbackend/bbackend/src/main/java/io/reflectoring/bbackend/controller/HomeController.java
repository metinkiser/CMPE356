package io.reflectoring.bbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Backend API is running! Available endpoints: /api/auth, /api/admin, /api/manager, /api/tickets");
    }
    
    @GetMapping("/api")
    public ResponseEntity<String> api() {
        return ResponseEntity.ok("API root endpoint. Please use specific endpoints.");
    }
} 