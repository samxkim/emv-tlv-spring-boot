package com.omni.webapp.models;

import javax.persistence.*;

@Entity
public class TagGroupsEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tagGroupsEntity_id")
    private TagGroupsEntity tagGroupsEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TagGroupsEntity getTagGroupsEntity() {
        return tagGroupsEntity;
    }

    public void setTagGroupsEntity(TagGroupsEntity tagGroupsEntity) {
        this.tagGroupsEntity = tagGroupsEntity;
    }
}
