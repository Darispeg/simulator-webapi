package com.developer.patrolsimulator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class UserResponse {
    private UUID Key;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String rank;
    private String ffaa;
    private String status;
    private Date created;
    private List<UUID> patrols = new ArrayList<>();
}