package com.developer.patrolsimulator.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PatchRequest {
    private String op;
    private String path;
    private String value;

    @Override
    public String toString() {
        return "[\n" +
                "{ \"op\": \"" + op + "\", " +
                "\"path\": \"" + path + "\", " +
                "\"value\": \"" + value + "\" }\n" +
                "]\n";
    }
}
