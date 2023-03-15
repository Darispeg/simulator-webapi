package com.developer.patrolsimulator.repository;

import com.developer.patrolsimulator.db.entities.PatrolsEntity;
import com.developer.patrolsimulator.db.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatrolRepository extends JpaRepository<PatrolsEntity, Long> {
    Optional<PatrolsEntity> findByPatrolKey(UUID patrolKey);

    List<Optional<PatrolsEntity>> getAllByUserEntity(UserEntity userKey);
}
