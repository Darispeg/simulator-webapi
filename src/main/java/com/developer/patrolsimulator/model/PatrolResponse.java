package com.developer.patrolsimulator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter @Setter
public class PatrolResponse {
    private UUID patrolKey;
    private Date created;
    private UUID mapKey;
    private UUID userKey;
    private float qualification;
    private int totalSeconds;
    private ResultResponse result;
    private RecognitionResponse recognition;
    private String username;
    private String map;
}