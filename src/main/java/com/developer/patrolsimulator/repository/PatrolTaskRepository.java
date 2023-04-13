package com.developer.patrolsimulator.repository;

import com.developer.patrolsimulator.db.entities.PatrolTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PatrolTaskRepository extends JpaRepository<PatrolTaskEntity, Long> {
    Optional<PatrolTaskEntity> findByTaskKey(UUID key);
}