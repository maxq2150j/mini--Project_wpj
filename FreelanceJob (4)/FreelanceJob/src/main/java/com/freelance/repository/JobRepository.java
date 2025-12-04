package com.freelance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelance.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, Long> {

	 boolean existsByTitle(String title);
}
