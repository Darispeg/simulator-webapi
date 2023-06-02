package com.developer.patrolsimulator.model;

import lombok.Getter;
import lombok.Setter;

import java.io.ByteArrayInputStream;

@Getter @Setter
public class ReportPatrols {
    private String fileName;
    private ByteArrayInputStream stream;
    private int length;
}
