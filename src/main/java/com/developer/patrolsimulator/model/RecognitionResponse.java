package com.developer.patrolsimulator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class RecognitionResponse {
    private UUID recognitionKey;
    private int watchtowers;
    private int tank;
    private int helicopter;
    private boolean alternativeRoute;
    private int mortar;
    private int jeep;
}
