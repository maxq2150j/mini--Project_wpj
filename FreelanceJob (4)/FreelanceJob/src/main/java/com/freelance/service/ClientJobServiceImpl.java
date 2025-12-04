package com.freelance.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.freelance.dto.ClientJobDTO;
import com.freelance.entities.ClientJobEntity;
import com.freelance.entities.UserEntity;
import com.freelance.repository.ClientJobRepository;
import com.freelance.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ClientJobServiceImpl implements ClientJobService {
	private static final Logger log = LoggerFactory.getLogger(ClientJobServiceImpl.class);

	private final ClientJobRepository clientjobRepository;
	private final UserRepository userRepository;

	@Override
	public ClientJobEntity createJob(ClientJobEntity job) {
		return clientjobRepository.save(job);
	}

	@Override
	public ClientJobEntity updateJob(Long id, ClientJobEntity job) {
		ClientJobEntity old = clientjobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
		old.setTitle(job.getTitle()); old.setDescription(job.getDescription()); old.setBudget(job.getBudget());
		return clientjobRepository.save(old);
	}

	@Override
	public void deleteJob(Long id) {
		clientjobRepository.deleteById(id);
	}

	@Override
	public ClientJobEntity getJob(Long id) {
		return clientjobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
	}

	@Override
	public List<ClientJobEntity> getJobsByClient(Long clientId) {
		return clientjobRepository.findByClientId(clientId);
	}

	@Override
	public List<ClientJobEntity> getAllJobs() {
		return clientjobRepository.findAll();
	}

	@Override
	public List<ClientJobDTO> getAllJobsWithClient() {
		List<ClientJobEntity> all = clientjobRepository.findAll();
		List<ClientJobDTO> dtoList = new ArrayList<>();
		log.info("getAllJobsWithClient - total jobs: {}", all.size());
		for (ClientJobEntity e : all) {
			ClientJobDTO d = new ClientJobDTO();
			d.setId(e.getId());
			d.setTitle(e.getTitle());
			d.setDescription(e.getDescription());
			d.setBudget(e.getBudget());
			d.setClientId(e.getClientId());
			if (e.getClientId() != null) {
				UserEntity user = userRepository.findById(e.getClientId()).orElse(null);
				if (user != null) {
					d.setClientName(user.getName());
					d.setClientEmail(user.getEmail());
					log.info("Job {} - clientId {} -> found user: {}", e.getId(), e.getClientId(), user.getEmail());
				} else {
					log.warn("Job {} - clientId {} -> no matching user found", e.getId(), e.getClientId());
				}
			} else {
				log.warn("Job {} - clientId is null", e.getId());
			}
			dtoList.add(d);
		}
		return dtoList;
	}

}


