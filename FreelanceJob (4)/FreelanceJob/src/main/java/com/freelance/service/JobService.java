package com.freelance.service;

import java.util.List;

import com.freelance.dto.ApiResponse;
import com.freelance.dto.JobRegDTO;
import com.freelance.entities.JobEntity;

public interface JobService {
	
	 ApiResponse createNewJob(JobRegDTO jobDTO);

	    JobEntity updateJob(Long id, JobRegDTO jobDTO);

	    JobEntity getJobById(Long id);

	    List<JobEntity> getAllJobs();

	    String deleteJob(Long id);

}
