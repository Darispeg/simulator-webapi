package com.developer.patrolsimulator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class UserFindByKeyResponse {
    private UUID userKey;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String rank;
    private String ffaa;
    private Date created;
    private List<PatrolResponse> patrols = new ArrayList<>();
}
