package com.developer.patrolsimulator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class UserRequest {
    private UUID Key;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String rank;
    private String ffaa;
    private String status;
}
