package com.developer.patrolsimulator.controller;

import com.developer.patrolsimulator.db.entities.MapEntity;
import com.developer.patrolsimulator.db.entities.PatrolsEntity;
import com.developer.patrolsimulator.db.entities.UserEntity;
import com.developer.patrolsimulator.model.MapResponse;
import com.developer.patrolsimulator.model.MappingModelResponseService;
import com.developer.patrolsimulator.model.PatchRequest;
import com.developer.patrolsimulator.model.PatrolResponse;
import com.developer.patrolsimulator.service.MapService;
import com.developer.patrolsimulator.service.PatrolsService;
import com.developer.patrolsimulator.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/v1/patrols")
public class PatrolController {
    @Autowired
    private PatrolsService _patrolService;

    @Autowired
    private MapService _mapService;

    @Autowired
    private UserService _userService;

    @Autowired
    private MappingModelResponseService _mappingResponse;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public ResponseEntity<List<PatrolResponse>> getAll(){
        List<PatrolResponse> listPatrolResponse = new ArrayList<>();
        for (PatrolsEntity patrolEntity : _patrolService.findAll()) {
            listPatrolResponse.add(_mappingResponse.buildPatrol(patrolEntity));
        }
        return ResponseEntity.ok(listPatrolResponse);
    }

    @GetMapping(value = "/{patrolKey}")
    public ResponseEntity<PatrolsEntity> getPatrolByKey(@PathVariable UUID patrolKey){
        return ResponseEntity.ok(_patrolService.getByPatrolKey(patrolKey));
    }

    @GetMapping("/users/{userKey}")
    public ResponseEntity<List<PatrolResponse>> gePatrolsByUserKey(@PathVariable UUID userKey){
        return ResponseEntity.ok(_patrolService.getAllPatrolsByUserEntity(userKey));
    }

    @PostMapping
    public ResponseEntity<PatrolsEntity> save(@RequestBody PatrolsEntity entity){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/patrols/save").toUriString());
        entity.setPatrolKey(UUID.randomUUID());
        return ResponseEntity.created(uri).body(_patrolService.save(entity));
    }

    @PostMapping("/users/{userKey}/maps/{mapKey}")
    public ResponseEntity<PatrolsEntity> savePatrol(@PathVariable UUID userKey, @PathVariable UUID mapKey, @RequestBody PatrolsEntity entity){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/patrol/save").toUriString());
        entity.setPatrolKey(UUID.randomUUID());
        MapEntity map = _mapService.getByMapKey(mapKey);
        if (map != null)
        {
            map.getPatrols().add(entity);
            entity.setMapEntity(map);
        }

        UserEntity user = _userService.getByUserKey(userKey);
        if (user != null)
        {
            user.getPatrols().add(entity);
            entity.setUserEntity(user);
        }

        return ResponseEntity.created(uri).body(_patrolService.save(entity));
    }

    @PutMapping("/{patrolKey}/patch")
    public ResponseEntity updatePatrolTotalSecond(@PathVariable UUID patrolKey, @RequestBody PatchRequest patch) throws IOException {
        InputStream in = new ByteArrayInputStream(patch.toString().getBytes());
        JsonPatch jsonPatch = objectMapper.readValue(in, JsonPatch.class);
        try{
            PatrolsEntity patrol = _patrolService.getByPatrolKey(patrolKey);
            PatrolsEntity patrolPatched = applyPatchToPatrol(jsonPatch, patrol);
            UserEntity user = _userService.getByUserKey(patrol.getUserEntity().getUserKey());
            patrolPatched.setUserEntity(user);
            _patrolService.save(patrolPatched);
            return ResponseEntity.ok().build();
        }catch (JsonPatchException | JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private PatrolsEntity applyPatchToPatrol(JsonPatch patch, PatrolsEntity targetPatrol) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetPatrol, JsonNode.class));
        return objectMapper.treeToValue(patched, PatrolsEntity.class);
    }
}
