package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.MapEntity;
import com.developer.patrolsimulator.db.entities.RecognitionResultEntity;
import com.developer.patrolsimulator.repository.RecognitionResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service(value = "ResultService")
public class RecognitionResultServiceImpl extends GenericServiceImpl<RecognitionResultEntity, Long> implements RecognitionResultService {

    @Autowired
    private RecognitionResultRepository recognitionResultRepository;

    @Override
    public JpaRepository<RecognitionResultEntity, Long> getDao() {
        return recognitionResultRepository;
    }

    @Override
    public RecognitionResultEntity getByRecognitionResultByResultKey(UUID resultKey) {
        Optional<RecognitionResultEntity> obj = recognitionResultRepository.findByResultKey(resultKey);
        if (obj.isPresent())
            return obj.get();
        return null;
    }
}
