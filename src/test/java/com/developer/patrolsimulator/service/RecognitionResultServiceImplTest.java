package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.RecognitionResultEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.datasource.url=jdbc:postgresql://localhost:5432/TestPatrolSimulator")
class RecognitionResultServiceImplTest {

    @Autowired
    @Qualifier(value = "ResultService")
    private RecognitionResultService resultService;

    private final static UUID UUID1 = UUID.fromString("446e153a-d288-4c8a-b7e0-c486416b4d74");

    RecognitionResultEntity resultEntity;

    @BeforeEach
    void setUp() {
        RecognitionResultEntity entity = new RecognitionResultEntity();
        entity.setResultKey(UUID1);
        resultEntity = resultService.save(entity);
    }

    @Test
    void FoundPatrolByKey() {
        RecognitionResultEntity found = resultService.getByRecognitionResultByResultKey(UUID1);
        assertNotNull(found);
    }

    @Test
    void NotFoundPatrolByKey() {
        RecognitionResultEntity notFound = resultService.getByRecognitionResultByResultKey(UUID.randomUUID());
        assertNull(notFound);
    }

    @AfterEach
    void tearDown(){
        resultService.delete(resultEntity.getId());
    }
}