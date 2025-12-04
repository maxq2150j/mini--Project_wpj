package com.freelance.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.freelance.custom_excpetions.ApiException;
import com.freelance.dto.ApiResponse;
import com.freelance.dto.JobRegDTO;
import com.freelance.entities.JobEntity;
import com.freelance.repository.JobRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class JobServiceImpl  implements JobService {
	
	private final JobRepository jobRepository;
	private final ModelMapper mapper;
	
	@Override
	
	public ApiResponse createNewJob(JobRegDTO jobDTO) {
		
		if(jobRepository.existsByTitle(jobDTO.getTitle())) {
			throw new ApiException("Job already exists with same title");
			
			
		}
		
		JobEntity entity = mapper.map(jobDTO, JobEntity.class);
		
		JobEntity saved = jobRepository.save(entity);
		
		return new ApiResponse(
			
			"New Job Created with ID = " + saved.getId(),
			"Success" );
		}
	
	
	 @Override
	    public JobEntity updateJob(Long id, JobRegDTO jobDTO) {
	        JobEntity old = jobRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Job Not Found"));

	        old.setTitle(jobDTO.getTitle());
	        old.setDescription(jobDTO.getDescription());
	        old.setBudget(jobDTO.getBudget());

	        return jobRepository.save(old);
	    }
	
	@Override
	public JobEntity getJobById(Long id) {
	    return jobRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Job Not Found With id : " + id));
	}
     
	
	
	 @Override
	    public List<JobEntity> getAllJobs() {
	        return jobRepository.findAll();
	    }

	    @Override
	    public String  deleteJob(Long id) {
	        jobRepository.deleteById(id);
	        return "Job Deleted Successfully";
	    }
		
		
	}
	


