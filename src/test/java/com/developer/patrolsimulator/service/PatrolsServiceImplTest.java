package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.PatrolsEntity;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.datasource.url=jdbc:postgresql://localhost:5432/TestPatrolSimulator")
class PatrolsServiceImplTest {

    @Autowired
    @Qualifier(value = "PatrolService")
    private PatrolsService patrolsService;

    PatrolsEntity patrols;

    @BeforeEach
    void setUp(){
        patrols = buildPatrol();
        patrols = patrolsService.save(patrols);
    }

    @Test
    void FoundPatrolByKey() {
        patrolsService.getByPatrolKey(patrols.getPatrolKey());
        assertNotNull(patrols);
    }

    @Test
    void NotFoundPatrolByKey() {
        PatrolsEntity PatrolNotFound = patrolsService.getByPatrolKey(UUID.randomUUID());
        assertNull(PatrolNotFound);
    }

    @AfterEach
    void tearDown(){
        patrolsService.delete(patrols.getId());
    }

    PatrolsEntity buildPatrol(){
        PatrolsEntity patrolsEntity = new PatrolsEntity();
        patrolsEntity.setPatrolKey(UUID.randomUUID());
        patrolsEntity.setAmountFormations(12);
        patrolsEntity.setAmountReconnaissance(12);
        return patrolsEntity;
    }
}