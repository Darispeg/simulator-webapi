package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.PatrolsEntity;
import com.developer.patrolsimulator.model.PatrolResponse;

import java.util.List;
import java.util.UUID;

public interface PatrolsService extends GenericService<PatrolsEntity, Long> {
    PatrolsEntity getByPatrolKey(UUID patrolKey);

    List<PatrolResponse> getAllPatrolsByUserEntity(UUID userKey);
}
