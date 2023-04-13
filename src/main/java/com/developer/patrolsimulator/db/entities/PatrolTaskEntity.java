package com.developer.patrolsimulator.db.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "patrol_task")
public class PatrolTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private UUID taskKey;

    @Column
    private boolean output;

    @Column
    private boolean pri;

    @Column
    private boolean pc;

    @Column
    private boolean formation;

    @Column
    private boolean pp;

    @Column
    private boolean px;

    @Column(name = "box_px")
    private boolean boxPx;

    @Column(name = "pro_px")
    private boolean proPx;

    @Column
    private boolean pro;

    @Column
    private boolean clover;

    @Column
    private boolean pd;

    @Column
    private boolean target;

    @Column
    private boolean prdo;

    private Date created;
    private Date update;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_patrol")
    private PatrolsEntity patrolsEntity;

    @PrePersist
    protected void onCreated(){
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        update = new Date();
    }
}
