package com.developer.patrolsimulator.repository;

import com.developer.patrolsimulator.db.entities.RecognitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RecognitionRepository extends JpaRepository <RecognitionEntity, Long> {
    Optional<RecognitionEntity> findByRecognitionKey(UUID recognitionKey);
}
