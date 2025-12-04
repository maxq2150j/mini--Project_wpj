package com.freelance.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freelance.dto.AuthRequestDTO;
import com.freelance.dto.AuthResponseDTO;
import com.freelance.dto.RegisterRequestDTO;
import com.freelance.entities.UserEntity;
import com.freelance.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public AuthResponseDTO register(RegisterRequestDTO req) {
        Optional<UserEntity> existing = userRepository.findByEmail(req.getEmail());
        if (existing.isPresent()) {
            return new AuthResponseDTO(false, "Email already registered");
        }

        UserEntity user = new UserEntity();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setRole(req.getRole() == null ? "client" : req.getRole());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        userRepository.save(user);
        return new AuthResponseDTO(true, "User registered successfully");
    }

    public AuthResponseDTO login(AuthRequestDTO req) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(req.getEmail());
        if (userOpt.isEmpty()) {
            return new AuthResponseDTO(false, "Invalid credentials");
        }

        UserEntity user = userOpt.get();
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            return new AuthResponseDTO(false, "Invalid credentials");
        }

        // generate a simple token (not JWT) — you can replace with real JWT later
        String token = UUID.randomUUID().toString();

        AuthResponseDTO resp = new AuthResponseDTO(true, "Login successful", user.getRole(), token, user.getId(), user.getName(), user.getEmail());
        return resp;
    }
}
