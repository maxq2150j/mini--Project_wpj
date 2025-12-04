package com.freelance.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelance.entities.TaskEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;
import com.freelance.service.TaskService;


import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskEntity createTask(@RequestBody TaskEntity task) {
        return taskService.createTask(task);
    }

    // ⭐ Add this
    @GetMapping
    public List<TaskEntity> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/freelancer/{id}")
    public List<TaskEntity> getTasksByFreelancer(@PathVariable Long id) {
        return taskService.getTasksByFreelancer(id);
    }

    @PutMapping("/{id}/complete")
    public TaskEntity markCompleted(@PathVariable Long id) {
        return taskService.markCompleted(id);
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<?> markPaid(@PathVariable Long id, @RequestParam(required = false) Long payerId, @RequestParam(required = false) Double amount) {
        try {
            TaskEntity t = taskService.markPaid(id, payerId, amount);
            return ResponseEntity.ok(t);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> err = new HashMap<>();
            err.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }
}
