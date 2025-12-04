package com.freelance.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freelance.entities.ClientJobEntity;
import com.freelance.service.ClientJobService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/client/jobs")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ClientJobController {

    private final ClientJobService clientJobService;

    @PostMapping
    public ClientJobEntity createJob(@RequestBody ClientJobEntity job) {
        return clientJobService.createJob(job);
    }

    @GetMapping
    public List<ClientJobEntity> getClientJobs(@RequestParam(required = false) Long clientId) {
        if (clientId == null) return clientJobService.getAllJobs();
        return clientJobService.getJobsByClient(clientId);
    }

    @GetMapping("/with-client")
    public java.util.List<com.freelance.dto.ClientJobDTO> getJobsWithClient() {
        return clientJobService.getAllJobsWithClient();
    }

    @GetMapping("/{id}")
    public ClientJobEntity getJobById(@PathVariable Long id) {
        return clientJobService.getJob(id);
    }

    @PutMapping("/{id}")
    public ClientJobEntity updateJob(@PathVariable Long id, @RequestBody ClientJobEntity job) {
        return clientJobService.updateJob(id, job);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        clientJobService.deleteJob(id);
    }

}
