package com.developer.patrolsimulator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class PatrolCreateRequest {
    private UUID mapKey;
}
