package com.developer.patrolsimulator.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "maps")
@Data
public class MapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private UUID mapKey;

    @Column
    private String name;

    @Column
    private String weather;

    @OneToMany(mappedBy = "mapEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnoreProperties("mapEntity")
    private List<PatrolsEntity> patrols = new ArrayList<PatrolsEntity>();
}
