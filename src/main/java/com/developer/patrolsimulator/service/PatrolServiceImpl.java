package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.PatrolsEntity;
import com.developer.patrolsimulator.db.entities.UserEntity;
import com.developer.patrolsimulator.model.MappingModelResponseService;
import com.developer.patrolsimulator.model.PatrolResponse;
import com.developer.patrolsimulator.repository.PatrolRepository;
import com.developer.patrolsimulator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service(value = "PatrolService")
public class PatrolServiceImpl extends GenericServiceImpl<PatrolsEntity, Long> implements PatrolsService {

    @Autowired
    private PatrolRepository patrolRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MappingModelResponseService mappingResponse;

    @Override
    public JpaRepository<PatrolsEntity, Long> getDao() {
        return patrolRepository;
    }

    @Override
    public PatrolsEntity getByPatrolKey(UUID patrolKey) {
        Optional<PatrolsEntity> obj = patrolRepository.findByPatrolKey(patrolKey);
        if (obj.isPresent())
            return obj.get();
        return null;
    }

    @Override
    public List<PatrolResponse> getAllPatrolsByUserEntity(UUID userKey) {
        Optional<UserEntity> user = userRepository.findByUserKey(userKey);
        List<PatrolResponse> listPatrolResponse = new ArrayList<>();
        if(user.isPresent()) {
            List<PatrolsEntity> returnList = new ArrayList<>();
            patrolRepository.getAllByUserEntity(user.get()).forEach(obj -> returnList.add(obj.get()));
            for (PatrolsEntity patrol: returnList) {
                listPatrolResponse.add(mappingResponse.buildPatrol(patrol));
            }
        }
        return listPatrolResponse;
    }
}