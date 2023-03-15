package com.developer.patrolsimulator.controller;

import com.developer.patrolsimulator.db.entities.UserEntity;
import com.developer.patrolsimulator.model.UserResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.*;

import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends ControllerTest {

    public final String url = "/api/v1/users";
    public static final String JSON_ROOT = "$";

    public static final String USER_RESPONSE_NAME = "Testing UserController response description";

    public static final Long userID1 = 1L;

    public static final Long userID2 = 2L;

    public static final UUID UUID1 = UUID.fromString("fc07c813-e15c-46e4-8a3f-9d001fd26f7d");

    public static final UUID UUID2 = UUID.fromString("fc07c813-e15c-46e4-8a3f-9d001fd26f7a");

    UserEntity userEntity1 = buildUserEntity(userID1, UUID1);
    UserEntity userEntity2 = buildUserEntity(userID2, UUID2);

    /*@Test
    void getAll() {
        ResponseEntity<List<UserResponse>> responseUsers = ResponseEntity.ok(new ArrayList<>(Arrays.asList(userEntity1, userEntity2)));

        Mockito.when(userController.getAll()).thenReturn(responseUsers);

        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath(JSON_ROOT + "[0].username", is(USER_RESPONSE_NAME)));
        }catch (Exception e) {
            fail();
        }
    }

    @Test
    void getByUserKey() {
        ResponseEntity<UserEntity> responseUser = ResponseEntity.ok(userEntity1);

        Mockito.when(userController.getByUserKey(UUID1)).thenReturn(responseUser);

        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get(url + "/" + UUID1).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath(JSON_ROOT + ".username", is(USER_RESPONSE_NAME)));
        }catch (Exception e){
            fail();
        }
    }

    @Test
    void saveUser() {
        UserEntity userEntityRequest = buildUserEntity(userID1, UUID1);

        Mockito.when(userController.saveUser(userEntityRequest))
                .thenReturn(ResponseEntity.created(URI.create(url))
                        .body(userEntity1));

        try {
            MockHttpServletRequestBuilder mockRequest =
                    MockMvcRequestBuilders.post(url)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(this.objectMapper
                                    .writeValueAsString(userEntityRequest));

            mockMvc
                    .perform(mockRequest)
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath(JSON_ROOT, notNullValue()))
                    .andExpect(jsonPath(JSON_ROOT + ".name", is(USER_RESPONSE_NAME)));
        } catch (Exception e) {
            fail();
        }
    }
*/
    UserEntity buildUserEntity(Long id, UUID key)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setUserKey(key);
        userEntity.setName(USER_RESPONSE_NAME);
        userEntity.setLastName(USER_RESPONSE_NAME);
        userEntity.setUsername(USER_RESPONSE_NAME);
        return userEntity;
    }
}