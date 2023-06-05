package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.PatrolTaskEntity;
import com.developer.patrolsimulator.db.entities.PatrolsEntity;
import com.developer.patrolsimulator.repository.PatrolRepository;
import com.developer.patrolsimulator.repository.PatrolTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service(value = "PatrolTaskService")
public class PatrolTaskServiceImpl extends GenericServiceImpl<PatrolTaskEntity, Long> implements PatrolTaskService{

    @Autowired
    private PatrolRepository patrolRepository;

    @Autowired
    private PatrolTaskRepository patrolTaskRepository;

    @Override
    public JpaRepository<PatrolTaskEntity, Long> getDao() {
        return patrolTaskRepository;
    }

    @Override
    public PatrolTaskEntity findByTaskKey(UUID key) {
        Optional<PatrolTaskEntity> obj = patrolTaskRepository.findByTaskKey(key);
        if (obj.isPresent())
            return obj.get();
        return null;
    }

    @Override
    public PatrolTaskEntity savePatrolTask(PatrolTaskEntity entity, UUID patrolKey) {
        PatrolTaskEntity taskEntity = getDao().save(entity);
        Optional<PatrolsEntity> patrolEntity = patrolRepository.findByPatrolKey(patrolKey);
        if (patrolEntity.isPresent()){
            patrolEntity.get().setQualification(calculatePatrolScore(taskEntity) * 10);
            patrolEntity.get().setTaskEntity(entity);
            taskEntity.setPatrolsEntity(patrolEntity.get());
            getDao().save(taskEntity);
            patrolRepository.save(patrolEntity.get());
        }
        return taskEntity;
    }

    private float calculatePatrolScore(PatrolTaskEntity taskEntity){
        float score = 0;
        score = (taskEntity.isOutput()) ? score + 1 : score + calculateOutputScore(taskEntity);
        score = (taskEntity.isPro()) ? score + 1 : score;
        score = (taskEntity.isClover()) ? score + 1 : score;
        score = (taskEntity.isPd()) ? score + 1 : score;
        score = (taskEntity.isTarget()) ? score + 1 : score;
        score = score / 5;
        return score;
    }

    private float calculateOutputScore(PatrolTaskEntity taskEntity){
        float outputScore = 0;
        outputScore = (taskEntity.isPri()) ? outputScore + 1 : outputScore;
        outputScore = (taskEntity.isPc()) ? outputScore + 1 : outputScore;
        outputScore = (taskEntity.isFormation()) ? outputScore + 1 : outputScore;
        outputScore = (taskEntity.isPp()) ? outputScore + 1 : outputScore;
        outputScore = (taskEntity.isPx()) ? outputScore + 1 : outputScore;
        outputScore = (taskEntity.isBoxPx()) ? outputScore + 1 : outputScore;
        outputScore = (taskEntity.isProPx()) ? outputScore + 1 : outputScore;
        outputScore = outputScore / 7;
        return outputScore;
    }
}
