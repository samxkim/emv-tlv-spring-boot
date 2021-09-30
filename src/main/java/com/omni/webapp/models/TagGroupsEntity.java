package com.omni.webapp.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TagGroupsEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long taggroups_id;

    @Column
    private String taggroups_name;

    @Column
    private String taggroups_description;
}
