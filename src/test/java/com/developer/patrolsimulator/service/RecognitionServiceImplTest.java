package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.RecognitionEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.datasource.url=jdbc:postgresql://localhost:5432/TestPatrolSimulator")
class RecognitionServiceImplTest {

    @Autowired
    @Qualifier(value = "RecognitionService")
    private RecognitionService recognitionService;

    private final static UUID UUID1 = UUID.fromString("446e153a-d288-4c8a-b7e0-c486416b4d74");

    RecognitionEntity recognitionEntity;

    @BeforeEach
    void setUp() {
        RecognitionEntity entity = new RecognitionEntity();
        entity.setRecognitionKey(UUID1);
        recognitionEntity = recognitionService.save(entity);
    }

    @Test
    void FoundPatrolByKey() {
        RecognitionEntity found = recognitionService.getByRecognitionKey(UUID1);
        assertNotNull(found);
    }

    @Test
    void NotFoundPatrolByKey() {
        RecognitionEntity notFound = recognitionService.getByRecognitionKey(UUID.randomUUID());
        assertNull(notFound);
    }

    @AfterEach
    void tearDown(){
        recognitionService.delete(recognitionEntity.getId());
    }
}