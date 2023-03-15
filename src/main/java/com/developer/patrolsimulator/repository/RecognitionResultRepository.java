package com.developer.patrolsimulator.repository;

import com.developer.patrolsimulator.db.entities.RecognitionEntity;
import com.developer.patrolsimulator.db.entities.RecognitionResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RecognitionResultRepository extends JpaRepository<RecognitionResultEntity, Long> {
    Optional<RecognitionResultEntity> findByResultKey(UUID resultKey);
}
