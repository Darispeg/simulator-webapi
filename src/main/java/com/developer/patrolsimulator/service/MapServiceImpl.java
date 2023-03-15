package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.MapEntity;
import com.developer.patrolsimulator.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service(value = "MapService")
public class MapServiceImpl extends GenericServiceImpl<MapEntity, Long> implements MapService{

    @Autowired
    private MapRepository mapRepository;

    @Override
    public JpaRepository<MapEntity, Long> getDao() {
        return mapRepository;
    }

    @Override
    public MapEntity getByMapKey(UUID mapKey) {
        Optional<MapEntity> obj = mapRepository.findByMapKey(mapKey);
        if (obj.isPresent())
            return obj.get();
        return null;
    }
}
