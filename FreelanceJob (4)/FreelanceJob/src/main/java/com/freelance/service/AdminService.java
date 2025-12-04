package com.freelance.service;

import com.freelance.entities.UserEntity;
import com.freelance.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, Object> getStats() {
        List<UserEntity> allUsers = userRepository.findAll();
        
        List<Map<String, Object>> clients = allUsers.stream()
            .filter(user -> "client".equalsIgnoreCase(user.getRole()))
            .map(user -> {
                Map<String, Object> clientMap = new HashMap<>();
                clientMap.put("id", user.getId());
                clientMap.put("name", user.getName());
                clientMap.put("email", user.getEmail());
                return clientMap;
            })
            .collect(Collectors.toList());

        List<Map<String, Object>> freelancers = allUsers.stream()
            .filter(user -> "freelancer".equalsIgnoreCase(user.getRole()))
            .map(user -> {
                Map<String, Object> freelancerMap = new HashMap<>();
                freelancerMap.put("id", user.getId());
                freelancerMap.put("name", user.getName());
                freelancerMap.put("email", user.getEmail());
                return freelancerMap;
            })
            .collect(Collectors.toList());

        Map<String, Object> stats = new HashMap<>();
        stats.put("clients", clients);
        stats.put("freelancers", freelancers);
        stats.put("totalClients", clients.size());
        stats.put("totalFreelancers", freelancers.size());

        return stats;
    }
}

