package com.developer.patrolsimulator.db.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "recognition_results")
@Data
public class RecognitionResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private UUID resultKey;

    @Column(name = "formations_qualification")
    private float formationsQualification;

    @Column(name = "techniques_recognition_qualification")
    private float techniquesRecognitionsQualification;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_patrol")
    private PatrolsEntity patrolsEntity;
}
