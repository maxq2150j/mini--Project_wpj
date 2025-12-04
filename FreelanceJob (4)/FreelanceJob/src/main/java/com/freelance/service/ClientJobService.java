package com.freelance.service;

import java.util.List;

import com.freelance.entities.ClientJobEntity;
import com.freelance.dto.ClientJobDTO;

public interface ClientJobService {
	
	 ClientJobEntity createJob(ClientJobEntity job);
	    ClientJobEntity updateJob(Long id, ClientJobEntity job);
	    void deleteJob(Long id);
	    ClientJobEntity getJob(Long id);
	    List<ClientJobEntity> getJobsByClient(Long clientId);
	    List<ClientJobEntity> getAllJobs();
		java.util.List<ClientJobDTO> getAllJobsWithClient();

}
