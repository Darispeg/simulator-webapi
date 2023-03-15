package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.UserEntity;
import com.developer.patrolsimulator.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = "spring.datasource.url=jdbc:postgresql://localhost:5432/TestPatrolSimulator")
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Autowired
    @Qualifier(value = "UserService")
    private UserServiceImpl userService;

    public static final Long userID1 = 1L;

    private static String USER_BODY = "Test";

    public static final UUID UUID1 = UUID.fromString("fc07c813-e15c-46e4-8a3f-9d001fd26f7d");

    private UserEntity userTest;

    @Test
    void FoundUserByUserKey() {
        UserEntity userResponse = userService.getByUserKey(UUID1);
        assertNotNull(userResponse);
    }

    @Test
    void NotFoundUserByUserKey() {
        UserEntity userResponse = userService.getByUserKey(UUID.randomUUID());
        assertEquals(userResponse, null);
    }

    @Test
    void FoundGetByUsernameAndPassword() {
        UserEntity userResponse = userService.getByUsernameAndPassword(USER_BODY, UUID1.toString());
        assertNotNull(userResponse);
    }

    @Test
    void NotFoundGetByUsernameAndPassword() {
        UserEntity userResponse = userService.getByUsernameAndPassword(USER_BODY, "PASSWORD");
        assertEquals(userResponse, null);
    }

    @Test
    void createUser(){
        userTest = userService.save(buildUserEntity());
        assertNotNull(userTest);
        userService.delete(userTest.getId());
    }

    @Test
    void getAllUserEntities(){
        List<UserEntity> users = userService.findAll();
        assertNotNull(users);
    }

    @Test
    void FoundGetUserById() {
        UserEntity user = userService.findById(1L);
        assertEquals(user.getId(), 1L);
    }

    @Test
    void NotFoundGetUserById() {
        UserEntity user = userService.findById(1000L);
        assertNull(user);
    }

    UserEntity buildUserEntity()
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserKey(UUID.randomUUID());
        userEntity.setName(USER_BODY);
        userEntity.setLastName(USER_BODY);
        userEntity.setUsername(UUID.randomUUID().toString());
        return userEntity;
    }
}