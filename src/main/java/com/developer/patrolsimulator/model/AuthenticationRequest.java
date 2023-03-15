package com.developer.patrolsimulator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class AuthenticationRequest {
    private String Username;
    private String Password;
}
