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
}