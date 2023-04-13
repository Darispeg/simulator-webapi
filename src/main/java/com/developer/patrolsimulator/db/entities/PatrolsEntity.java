package com.developer.patrolsimulator.db.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "patrols")
@Data
public class PatrolsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private UUID patrolKey;

    private Date created;
    private Date update;

    @Column(columnDefinition = "decimal default 0.0")
    private float qualification;

    @Column(columnDefinition = "integer default 0")
    private int totalSeconds;

    @PrePersist
    protected void onCreated(){
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        update = new Date();
    }

    @OneToOne(mappedBy = "patrolsEntity")
    @JsonIgnoreProperties("patrolsEntity")
    private RecognitionEntity recognitionEntity;

    @OneToOne(mappedBy = "patrolsEntity")
    @JsonIgnoreProperties("patrolsEntity")
    private RecognitionResultEntity recognitionResultEntity;

    @OneToOne(mappedBy = "patrolsEntity")
    @JsonIgnoreProperties("patrolsEntity")
    private PatrolTaskEntity taskEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_user")
    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_map")
    private MapEntity mapEntity;

    @JsonBackReference
    public UserEntity getUserEntity(){
        return this.userEntity;
    }
}