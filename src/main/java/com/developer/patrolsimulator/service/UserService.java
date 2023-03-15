package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService extends GenericService<UserEntity, Long> {
    UserEntity getByUserKey(UUID userKey);
    UserEntity getByUsernameAndPassword(String username, String password);
    List<UserEntity> getAllByOrderByLastNameAsc();
}
