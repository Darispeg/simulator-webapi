package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.RecognitionEntity;
import com.developer.patrolsimulator.repository.RecognitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service(value = "RecognitionService")
public class RecognitionServiceImpl extends GenericServiceImpl <RecognitionEntity, Long> implements RecognitionService {

    @Autowired
    private RecognitionRepository recognitionRepository;

    @Override
    public JpaRepository<RecognitionEntity, Long> getDao() {
        return recognitionRepository;
    }

    @Override
    public RecognitionEntity getByRecognitionKey(UUID recognitionKey) {
        Optional<RecognitionEntity> obj = recognitionRepository.findByRecognitionKey(recognitionKey);
        if (obj.isPresent())
            return obj.get();
        return null;
    }
}
