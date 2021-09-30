package com.omni.webapp.models;

import javax.persistence.*;

public class TagExamplesEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long tagexamples_id;

    @Column()
    private Lob tagexamples_data;
}
