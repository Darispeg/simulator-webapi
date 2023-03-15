package com.developer.patrolsimulator.controller;

import com.developer.patrolsimulator.db.entities.PatrolsEntity;
import com.developer.patrolsimulator.db.entities.RecognitionResultEntity;
import com.developer.patrolsimulator.service.PatrolsService;
import com.developer.patrolsimulator.service.RecognitionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/recognition-results")
public class RecognitionResultController {
    @Autowired
    private RecognitionResultService _resultService;

    @Autowired
    private PatrolsService _patrolService;

    @GetMapping
    public ResponseEntity<List<RecognitionResultEntity>> getAll(){
        return ResponseEntity.ok(_resultService.findAll());
    }

    @GetMapping(value = "/{resultKey}")
    public ResponseEntity<RecognitionResultEntity> getResultByKey(@PathVariable UUID resultKey){
        return ResponseEntity.ok(_resultService.getByRecognitionResultByResultKey(resultKey));
    }

    @PostMapping
    public ResponseEntity<RecognitionResultEntity> save(@RequestBody RecognitionResultEntity entity){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/recognition-results/save").toUriString());
        entity.setResultKey(UUID.randomUUID());
        return ResponseEntity.created(uri).body(_resultService.save(entity));
    }

    @PostMapping(value = "/patrol/{patrolKey}")
    public ResponseEntity<RecognitionResultEntity> saveResult(@PathVariable UUID patrolKey, @RequestBody RecognitionResultEntity entity){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/recognition-results/save").toUriString());
        entity.setResultKey(UUID.randomUUID());
        Optional<PatrolsEntity> patrol = Optional.ofNullable(_patrolService.getByPatrolKey(patrolKey));
        patrol.get().setRecognitionResultEntity(entity);
        entity.setPatrolsEntity(patrol.get());

        return ResponseEntity.created(uri).body(_resultService.save(entity));
    }
}
