package com.developer.patrolsimulator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class PatrolTaskResponse {

    private UUID taskKey;
    private boolean OUTPUT;
    private boolean PRI;
    private boolean PC;
    private boolean COLUMN;
    private boolean PP;
    private boolean PX;
    private boolean BOX_PX;
    private boolean PRO_PX;
    private boolean PRO;
    private boolean CLOVER;
    private boolean PD;
    private boolean TARGET;
    private boolean PRDO;
}
