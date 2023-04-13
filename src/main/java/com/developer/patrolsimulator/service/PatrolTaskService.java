package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.PatrolTaskEntity;

import java.util.UUID;

public interface PatrolTaskService extends GenericService<PatrolTaskEntity, Long> {
    PatrolTaskEntity findByTaskKey(UUID key);

    PatrolTaskEntity savePatrolTask(PatrolTaskEntity entity, UUID patrolKey);
}
