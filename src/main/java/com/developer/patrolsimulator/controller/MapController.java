package com.developer.patrolsimulator.controller;

import com.developer.patrolsimulator.db.entities.MapEntity;
import com.developer.patrolsimulator.model.MapFindByKey;
import com.developer.patrolsimulator.model.MapResponse;
import com.developer.patrolsimulator.model.MappingModelResponseService;
import com.developer.patrolsimulator.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/maps")
public class MapController {

    @Autowired
    private MapService _mapService;

    @Autowired
    private MappingModelResponseService _mappingResponse;

    @GetMapping
    public ResponseEntity<List<MapResponse>> getAll(){
        List<MapResponse> listMapResponse = new ArrayList<>();
        for (MapEntity mapEntity : _mapService.findAll()) {
            listMapResponse.add(_mappingResponse.buildMap(mapEntity));
        }
        return ResponseEntity.ok(listMapResponse);
    }

    @GetMapping(value = "/{mapKey}")
    public ResponseEntity<MapFindByKey> getByMapKey(@PathVariable UUID mapKey){
        return ResponseEntity.ok(_mappingResponse.buildMapFindByKey(_mapService.getByMapKey(mapKey)));
    }

    @PostMapping
    public ResponseEntity<MapEntity> save(@RequestBody MapEntity entity){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/maps/save").toUriString());
        entity.setMapKey(UUID.randomUUID());
        return ResponseEntity.created(uri).body(_mapService.save(entity));
    }
}
