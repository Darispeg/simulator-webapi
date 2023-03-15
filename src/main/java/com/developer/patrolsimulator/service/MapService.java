package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.MapEntity;

import java.util.UUID;

public interface MapService extends GenericService<MapEntity, Long> {
    MapEntity getByMapKey(UUID mapKey);
}
