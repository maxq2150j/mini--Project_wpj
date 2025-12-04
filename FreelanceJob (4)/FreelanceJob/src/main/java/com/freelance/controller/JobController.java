package com.freelance.controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelance.dto.ApiResponse;
import com.freelance.dto.JobRegDTO;
import com.freelance.service.JobService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/jobs")
@AllArgsConstructor
public class JobController {
	
	private final JobService jobService;
	
	@PostMapping("/")
	public ResponseEntity<?> createJob(@RequestBody JobRegDTO dto){
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(jobService.createNewJob(dto));
		} catch (RuntimeException ex) {
			System.out.println("Error : " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(ex.getMessage(), "Failed"));
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getAllJobs(){
		return ResponseEntity.ok(jobService.getAllJobs());
	}

	// GET JOB BY ID
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		return ResponseEntity.ok(jobService.getJobById(id));
	}

	// UPDATE JOB
	@PutMapping("/{id}")
	public ResponseEntity<?> updateJob(@PathVariable Long id, @RequestBody JobRegDTO dto){
		return ResponseEntity.ok(jobService.updateJob(id, dto));
	}

	// DELETE JOB
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteJob(@PathVariable Long id){
		jobService.deleteJob(id);
		return ResponseEntity.ok(new ApiResponse("Job Deleted Successfully", "Success"));
	}

}
