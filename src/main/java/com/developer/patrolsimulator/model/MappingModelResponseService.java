package com.developer.patrolsimulator.model;

import com.developer.patrolsimulator.db.entities.*;
import org.springframework.stereotype.Service;

@Service
public class MappingModelResponseService {

    // Building to Model For GetAllUser Method
    public UserResponse buildUser(UserEntity entity){
        UserResponse response = new UserResponse();
        response.setKey(entity.getUserKey());
        response.setUsername(entity.getUsername());
        response.setLastName(entity.getLastName());
        response.setName(entity.getName());
        response.setCreated(entity.getCreated());
        response.setFfaa(entity.getFfaa());
        response.setEmail(entity.getEmail());
        response.setRank(entity.getRank());
        response.setPassword(entity.getPassword());
        response.setStatus(entity.getStatus());
        if(entity.getPatrols() != null)
        {
            for (PatrolsEntity patrol: entity.getPatrols()) {
                response.getPatrols().add(patrol.getPatrolKey());
            }
        }

        return response;
    }

    // Building to Model for FindUserById
    public UserFindByKeyResponse buildUserFindByKey(UserEntity entity){
        UserFindByKeyResponse response = new UserFindByKeyResponse();
        response.setUserKey(entity.getUserKey());
        response.setUsername(entity.getUsername());
        response.setLastName(entity.getLastName());
        response.setName(entity.getName());
        response.setCreated(entity.getCreated());
        response.setFfaa(entity.getFfaa());
        response.setEmail(entity.getEmail());
        response.setRank(entity.getRank());
        response.setPassword(entity.getPassword());

        if(entity.getPatrols() != null)
        {
            for (PatrolsEntity patrol: entity.getPatrols()) {
                response.getPatrols().add(buildPatrol(patrol));
            }
        }

        return response;
    }

    public PatrolResponse buildPatrol(PatrolsEntity entity) {
        PatrolResponse patrolResponse = new PatrolResponse();
        patrolResponse.setPatrolKey(entity.getPatrolKey());
        patrolResponse.setCreated(entity.getCreated());
        patrolResponse.setQualification(entity.getQualification());
        patrolResponse.setTotalSeconds(entity.getTotalSeconds());
        patrolResponse.setMapKey(entity.getMapEntity().getMapKey());
        patrolResponse.setMap(entity.getMapEntity().getName());
        if(entity.getUserEntity() != null)
        {
            patrolResponse.setUserKey(entity.getUserEntity().getUserKey());
            patrolResponse.setUsername(String.join(" ", entity.getUserEntity().getLastName(), entity.getUserEntity().getName()));
        }
        if (entity.getRecognitionEntity() != null)
            patrolResponse.setRecognition(buildRecognition(entity.getRecognitionEntity()));

        if (entity.getRecognitionResultEntity() != null)
            patrolResponse.setResult(buildResult(entity.getRecognitionResultEntity()));

        if(entity.getTaskEntity() != null)
            patrolResponse.setTaskResponse(buildPatrolTask(entity.getTaskEntity()));

        return patrolResponse;
    }

    public RecognitionResponse buildRecognition(RecognitionEntity entity){
        RecognitionResponse recognition = new RecognitionResponse();
        recognition.setRecognitionKey(entity.getRecognitionKey());
        recognition.setHelicopter(entity.getHelicopter());
        recognition.setJeep(entity.getJeep());
        recognition.setMortar(entity.getMortar());
        recognition.setTank(entity.getTank());
        recognition.setWatchtowers(entity.getWatchtowers());
        return recognition;
    }

    public ResultResponse buildResult(RecognitionResultEntity entity){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setResultKey(entity.getResultKey());
        resultResponse.setTechniquesRecognitionsQualification(entity.getTechniquesRecognitionsQualification());
        resultResponse.setFormationsQualification(entity.getFormationsQualification());
        return resultResponse;
    }

    public MapResponse buildMap(MapEntity entity){
        MapResponse mapResponse = new MapResponse();
        mapResponse.setMapKey(entity.getMapKey());
        mapResponse.setName(entity.getName());
        if(entity.getPatrols() != null) {
            for (PatrolsEntity patrol : entity.getPatrols()) {
                mapResponse.getPatrols().add(patrol.getPatrolKey());
            }
        }
        return mapResponse;
    }

    public MapFindByKey buildMapFindByKey(MapEntity entity){
        MapFindByKey mapResponse = new MapFindByKey();
        mapResponse.setMapKey(entity.getMapKey());
        mapResponse.setName(entity.getName());
        if(entity.getPatrols() != null) {
            for (PatrolsEntity patrol : entity.getPatrols()) {
                mapResponse.getPatrols().add(buildPatrol(patrol));
            }
        }
        return mapResponse;
    }

    public PatrolTaskResponse buildPatrolTask(PatrolTaskEntity taskEntity){
        PatrolTaskResponse taskResponse = new PatrolTaskResponse();
        taskResponse.setOUTPUT(taskEntity.isOutput());
        taskResponse.setPRI(taskEntity.isPri());
        taskResponse.setPC(taskEntity.isPc());
        taskResponse.setCOLUMN(taskEntity.isFormation());
        taskResponse.setPP(taskEntity.isPp());
        taskResponse.setPX(taskEntity.isPx());
        taskResponse.setBOX_PX(taskEntity.isBoxPx());
        taskResponse.setPRO_PX(taskEntity.isProPx());
        taskResponse.setPRO(taskEntity.isPro());
        taskResponse.setCLOVER(taskEntity.isClover());
        taskResponse.setPD(taskEntity.isPd());
        taskResponse.setTARGET(taskEntity.isTarget());
        taskResponse.setPRDO(taskEntity.isPrdo());
        return taskResponse;
    }

    public PatrolTaskEntity buildPatrolTaskEntity(PatrolTaskRequest task){
        PatrolTaskEntity entity = new PatrolTaskEntity();
        entity.setOutput(task.isOUTPUT());
        entity.setPri(task.isPRI());
        entity.setPc(task.isPC());
        entity.setFormation(task.isCOLUMN());
        entity.setPp(task.isPP());
        entity.setPx(task.isPX());
        entity.setBoxPx(task.isBOX_PX());
        entity.setProPx(task.isPRO_PX());
        entity.setPro(task.isPRO());
        entity.setClover(task.isCLOVER());
        entity.setPd(task.isPD());
        entity.setTarget(task.isTARGET());
        entity.setPrdo(task.isPRDO());
        return entity;
    }
}