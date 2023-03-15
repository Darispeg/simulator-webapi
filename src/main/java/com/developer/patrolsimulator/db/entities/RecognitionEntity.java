package com.developer.patrolsimulator.db.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "recognitions")
@Data
public class RecognitionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private UUID recognitionKey;

    @Column
    private int watchtowers;

    @Column
    private int tank;

    @Column
    private int helicopter;

    @Column(name = "alternative_route")
    private boolean alternativeRoute;

    @Column
    private int mortar;

    @Column
    private int jeep;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_patrol")
    private PatrolsEntity patrolsEntity;
}
