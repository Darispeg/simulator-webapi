package com.developer.patrolsimulator.controller;

import com.developer.patrolsimulator.db.entities.UserEntity;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest extends ControllerTest {

    public final String url = "/api/v1/auth";
    public static final String JSON_ROOT = "$";

    public static final String AUTH_RESPONSE = "Testing AuthController response description";
    public static final String USERNAME = "drago_test";
    public static final String PASSWORD = "P455W0RD";

    public static final Long mapID1 = 1L;

    public static final UUID UUID1 = UUID.fromString("fc07c813-e15c-46e4-8a3f-9d001fd26f7d");

    UserEntity userAuthResponse = buildUserEntity(mapID1, UUID1);

    @Test
    void auth() {
        Mockito.when(userService.getByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(userAuthResponse);

        UserEntity userAuth = userService.getByUsernameAndPassword(USERNAME, PASSWORD);

        assertNotNull(userAuth);
        assertEquals(USERNAME, userAuth.getUsername());
    }

    UserEntity buildUserEntity(Long id, UUID key)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setUserKey(key);
        userEntity.setName(AUTH_RESPONSE);
        userEntity.setLastName(AUTH_RESPONSE);
        userEntity.setUsername(USERNAME);
        userEntity.setPassword(PASSWORD);
        return userEntity;
    }
}