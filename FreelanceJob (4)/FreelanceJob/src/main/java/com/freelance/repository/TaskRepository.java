package com.freelance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelance.entities.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByFreelancerId(Long freelancerId);

}
