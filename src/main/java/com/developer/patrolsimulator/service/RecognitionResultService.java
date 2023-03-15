package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.RecognitionResultEntity;

import java.util.UUID;

public interface RecognitionResultService extends GenericService <RecognitionResultEntity, Long>{
    RecognitionResultEntity getByRecognitionResultByResultKey(UUID resultKey);
}
