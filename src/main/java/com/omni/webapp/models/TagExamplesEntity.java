package com.omni.webapp.models;

import javax.persistence.*;

@Entity
public class TagExamplesEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String data;
}
