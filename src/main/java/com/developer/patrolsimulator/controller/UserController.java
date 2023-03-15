package com.developer.patrolsimulator.controller;

import com.developer.patrolsimulator.db.entities.*;
import com.developer.patrolsimulator.model.*;
import com.developer.patrolsimulator.service.MapService;
import com.developer.patrolsimulator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService _service;

    @Autowired
    private MapService _mapService;

    @Autowired
    private MappingModelResponseService mapping;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        List<UserResponse> listUserResponse = new ArrayList<>();
        for (UserEntity user: _service.findAll()) {
            listUserResponse.add(mapping.buildUser(user));
        }
        return ResponseEntity.ok(listUserResponse);
    }

    @GetMapping("/OrderBy")
    public ResponseEntity<List<UserEntity>> getAllOrderByLastNameAsc(){
        return ResponseEntity.ok(_service.getAllByOrderByLastNameAsc());
    }


    @GetMapping(value = "/{userKey}")
    public ResponseEntity<UserFindByKeyResponse> getByUserKey(@PathVariable UUID userKey){
        return ResponseEntity.ok(mapping.buildUserFindByKey(_service.getByUserKey(userKey)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserEntity entity){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
        entity.setUserKey(UUID.randomUUID());
        return ResponseEntity.created(uri).body(mapping.buildUser(_service.save(entity)));
    }

    @PostMapping("{id}/patrol")
    public ResponseEntity<UserEntity> savePatrol(@PathVariable Long id, @RequestBody PatrolsEntity entity){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
        entity.setPatrolKey(UUID.randomUUID());
        Optional<UserEntity> userEntity = Optional.ofNullable(_service.findById(id));
        userEntity.get().getPatrols().add(entity);
        entity.setUserEntity(userEntity.get());

        Optional<MapEntity> map = Optional.ofNullable(_mapService.findById(1L));
        entity.setMapEntity(map.get());

        return ResponseEntity.created(uri).body(_service.save(userEntity.get()));
    }

    @PutMapping("/{key}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID key, @RequestBody UserRequest user) {
        Optional<UserEntity> userEntity = Optional.ofNullable(_service.getByUserKey(key));

        if (userEntity.isPresent()){
            userEntity.get().setUsername(user.getUsername());
            userEntity.get().setName(user.getName());
            userEntity.get().setLastName(user.getLastName());
            userEntity.get().setEmail(user.getEmail());
            userEntity.get().setRank(user.getRank());
            userEntity.get().setFfaa(user.getFfaa());
            userEntity.get().setStatus(user.getStatus());

            UserResponse response = mapping.buildUser(_service.save(userEntity.get()));
            return ResponseEntity.ok(response);
        }
        else {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
        }
    }
}
