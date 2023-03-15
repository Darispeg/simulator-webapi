package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.MapEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.datasource.url=jdbc:postgresql://localhost:5432/TestPatrolSimulator")
class MapServiceImplTest {

    @Autowired
    @Qualifier(value = "MapService")
    private MapService mapService;

    private final static UUID UUID1 = UUID.fromString("446e153a-d288-4c8a-b7e0-c486416b4d74");

    MapEntity mapEntity;

    @BeforeEach
    void setUp() {
        MapEntity entity = new MapEntity();
        entity.setId(2L);
        entity.setMapKey(UUID1);
        entity.setName("RandomMap");
        entity.setWeather("test");
        entity.setPatrols(null);
        mapEntity = mapService.save(entity);
    }

    @Test
    void FoundMapByKey() {
        MapEntity found = mapService.getByMapKey(UUID1);
        assertNotNull(found);
    }

    @Test
    void NotFoundMapByKey() {
        MapEntity notFound = mapService.getByMapKey(UUID.randomUUID());
        assertNull(notFound);
    }

    @AfterEach
    void tearDown(){
        mapService.delete(mapEntity.getId());
    }
}