package com.developer.patrolsimulator.controller;

import com.developer.patrolsimulator.db.entities.PatrolTaskEntity;
import com.developer.patrolsimulator.model.MappingModelResponseService;
import com.developer.patrolsimulator.model.PatrolTaskRequest;
import com.developer.patrolsimulator.service.PatrolTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "https://6463cd1ab550f106ff658550--iridescent-cactus-a57f82.netlify.app/")
@RequestMapping(value = "/api/v1")
public class PatrolTaskController {
    @Autowired
    private PatrolTaskService taskService;

    @Autowired
    private MappingModelResponseService _mappingResponse;


    @GetMapping(value = "/patrol-tasks/{key}")
    public ResponseEntity<PatrolTaskEntity> getPatrolByKey(@PathVariable UUID key){
        return ResponseEntity.ok(taskService.findByTaskKey(key));
    }

    @PostMapping(value = "/patrols/{patrolKey}/tasks")
    public ResponseEntity<PatrolTaskEntity> save(@RequestBody PatrolTaskRequest task, @PathVariable UUID patrolKey){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/patrol-task").toUriString());
        PatrolTaskEntity entity = _mappingResponse.buildPatrolTaskEntity(task);
        entity.setTaskKey(UUID.randomUUID());
        System.out.println(task.isPD());
        return ResponseEntity.created(uri).body(taskService.savePatrolTask(entity, patrolKey));
    }
}
