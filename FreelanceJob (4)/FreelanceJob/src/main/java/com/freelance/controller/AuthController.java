package com.freelance.controller;

import com.freelance.dto.AuthRequestDTO;
import com.freelance.dto.AuthResponseDTO;
import com.freelance.dto.RegisterRequestDTO;
import com.freelance.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO req) {
        AuthResponseDTO resp = authService.register(req);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO req) {
        AuthResponseDTO resp = authService.login(req);
        return ResponseEntity.ok(resp);
    }
}
