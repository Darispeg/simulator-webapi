package com.developer.patrolsimulator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class MapResponse {
    private UUID mapKey;
    private String name;
    private List<UUID> patrols = new ArrayList<>();
}
