package com.developer.patrolsimulator.controller;

import com.developer.patrolsimulator.db.entities.MapEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MapControllerTest extends ControllerTest {

    public final String url = "/api/v1/maps";
    public static final String JSON_ROOT = "$";

    public static final String MAP_RESPONSE = "Testing MapController response description";

    public static final Long mapID1 = 1L;

    public static final Long mapID2 = 2L;

    public static final UUID UUID1 = UUID.fromString("fc07c813-e15c-46e4-8a3f-9d001fd26f7d");

    public static final UUID UUID2 = UUID.fromString("fc07c813-e15c-46e4-8a3f-9d001fd26f7a");

    MapEntity mapEntityResponse1 = buildMapEntity(mapID1, UUID1);
    MapEntity mapEntityResponse2 = buildMapEntity(mapID2, UUID2);

/*    @Test
    void getAll() {
        ResponseEntity<List<MapEntity>> responseMaps = ResponseEntity.ok(new ArrayList<>(Arrays.asList(mapEntityResponse1, mapEntityResponse2)));

        Mockito.when(mapController.getAll()).thenReturn(responseMaps);

        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath(JSON_ROOT + "[0].name", is(MAP_RESPONSE)));
        }catch (Exception e) {
            fail();
        }
    }

    @Test
    void getByMapKey() {
        ResponseEntity<MapEntity> responseUser = ResponseEntity.ok(mapEntityResponse1);

        Mockito.when(mapController.getByMapKey(UUID1)).thenReturn(responseUser);

        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get(url + "/" + UUID1).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath(JSON_ROOT + ".name", is(MAP_RESPONSE)));
        }catch (Exception e){
            fail();
        }
    }
*/
    @Test
    void save() {
        MapEntity mapRequest = buildMapEntity(mapID1, UUID1);

        Mockito.when(mapController.save(mapRequest)).thenReturn(ResponseEntity.created(URI.create(url)).body(mapEntityResponse1));

        try {
            MockHttpServletRequestBuilder mockRequest =
                    MockMvcRequestBuilders.post(url)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(this.objectMapper.writeValueAsString(mapRequest));

            mockMvc
                    .perform(mockRequest)
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath(JSON_ROOT, notNullValue()))
                    .andExpect(jsonPath(JSON_ROOT + ".name", is(MAP_RESPONSE)));
        } catch (Exception e) {
            fail();
        }
    }

    MapEntity buildMapEntity(Long id, UUID key){
        MapEntity mapEntity = new MapEntity();
        mapEntity.setId(id);
        mapEntity.setMapKey(key);
        mapEntity.setName(MAP_RESPONSE);
        return mapEntity;
    }
}