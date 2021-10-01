package com.omni.webapp.models;

import javax.persistence.*;

@Entity
public class TagGroupsEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;
}
