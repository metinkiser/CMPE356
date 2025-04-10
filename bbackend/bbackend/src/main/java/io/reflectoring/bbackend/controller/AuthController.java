package io.reflectoring.bbackend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.reflectoring.bbackend.dto.ForgotPasswordRequest;
import io.reflectoring.bbackend.dto.LoginRequest;
import io.reflectoring.bbackend.dto.RegisterRequest;
import io.reflectoring.bbackend.entity.User;
import io.reflectoring.bbackend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
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