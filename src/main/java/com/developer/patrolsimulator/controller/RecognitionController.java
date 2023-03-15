package com.developer.patrolsimulator.controller;

import com.developer.patrolsimulator.db.entities.PatrolsEntity;
import com.developer.patrolsimulator.db.entities.RecognitionEntity;
import com.developer.patrolsimulator.service.PatrolsService;
import com.developer.patrolsimulator.service.RecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/recognitions")
public class RecognitionController {

    @Autowired
    private RecognitionService _recognitionService;

    @Autowired
    private PatrolsService _patrolService;

    @GetMapping
    public ResponseEntity<List<RecognitionEntity>> getAll(){
        return ResponseEntity.ok(_recognitionService.findAll());
    }

    @GetMapping(value = "/{recognitionKey}")
    public ResponseEntity<RecognitionEntity> getRecognitionByKey(@PathVariable UUID recognitionKey){
        return ResponseEntity.ok(_recognitionService.getByRecognitionKey(recognitionKey));
    }

    @PostMapping
    public ResponseEntity<RecognitionEntity> save(@RequestBody RecognitionEntity entity){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/recognitions/save").toUriString());
        entity.setRecognitionKey(UUID.randomUUID());
        return ResponseEntity.created(uri).body(_recognitionService.save(entity));
    }

    @PostMapping(value = "/patrol/{patrolKey}")
    public ResponseEntity<RecognitionEntity> saveRecognition(@PathVariable UUID patrolKey, @RequestBody RecognitionEntity entity){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/recognitions/save").toUriString());
        entity.setRecognitionKey(UUID.randomUUID());
        Optional<PatrolsEntity> patrol = Optional.ofNullable(_patrolService.getByPatrolKey(patrolKey));
        patrol.get().setRecognitionEntity(entity);
        entity.setPatrolsEntity(patrol.get());

        return ResponseEntity.created(uri).body(_recognitionService.save(entity));
    }
}