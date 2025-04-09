package io.reflectoring.bbackend.service;

import io.reflectoring.bbackend.dto.RegisterRequest;
import io.reflectoring.bbackend.entity.User;
import io.reflectoring.bbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(request.getPassword());
        user.setRoleId(2L);
        user.setIsApproved(false);
        
        return userRepository.save(user);
    }
} 