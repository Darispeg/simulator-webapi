package com.developer.patrolsimulator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class ResultResponse {
    private UUID resultKey;
    private float formationsQualification;
    private float techniquesRecognitionsQualification;
    private float recognitionQualification;
    private float qualification;
    private int totalSeconds;
}
