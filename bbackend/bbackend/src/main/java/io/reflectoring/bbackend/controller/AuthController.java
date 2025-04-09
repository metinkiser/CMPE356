package io.reflectoring.bbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.reflectoring.bbackend.dto.LoginRequest;
import io.reflectoring.bbackend.dto.RegisterRequest;
import io.reflectoring.bbackend.dto.ForgotPasswordRequest;
import java.util.Map;
import io.reflectoring.bbackend.service.AuthService;
import io.reflectoring.bbackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok().body(Map.of(
            "token", "dummy-token",
            "role", "admin"
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User user = authService.register(request);
            return ResponseEntity.ok()
                .body(Map.of(
                    "message", "Registration successful",
                    "userId", user.getUserId()
                ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        // Geçici olarak başarılı yanıt dönüyoruz
        return new ResponseEntity<>("Password reset email sent", HttpStatus.OK);
    }
} 