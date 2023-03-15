package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.RecognitionEntity;

import java.util.UUID;

public interface RecognitionService extends GenericService<RecognitionEntity, Long> {
    RecognitionEntity getByRecognitionKey(UUID recognitionKey);
}
