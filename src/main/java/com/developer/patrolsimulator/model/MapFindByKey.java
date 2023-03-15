package com.developer.patrolsimulator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class MapFindByKey {
    private UUID mapKey;
    private String name;
    private List<PatrolResponse> patrols = new ArrayList<>();
}
