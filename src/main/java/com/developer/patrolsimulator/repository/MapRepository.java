package com.developer.patrolsimulator.repository;

import com.developer.patrolsimulator.db.entities.MapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MapRepository extends JpaRepository<MapEntity, Long> {
    Optional<MapEntity> findByMapKey(UUID mapKey);
}
