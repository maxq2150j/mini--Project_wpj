package com.freelance.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelance.dto.SimpleUserDTO;
import com.freelance.entities.UserEntity;
import com.freelance.repository.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public SimpleUserDTO getUser(@PathVariable Long id) {
        Optional<UserEntity> u = userRepository.findById(id);
        if (u.isPresent()) {
            UserEntity user = u.get();
            return new SimpleUserDTO(user.getId(), user.getName());
        }
        return null;
    }
}
