package com.freelance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freelance.entities.ClientJobEntity;

@Repository
public interface ClientJobRepository extends JpaRepository<ClientJobEntity, Long> {
	List<ClientJobEntity> findByClientId(Long clientId);

}
