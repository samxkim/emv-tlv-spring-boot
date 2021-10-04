package com.omni.webapp.models;

import javax.persistence.*;

@Entity
public class TagExamplesEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "TagExample_ID", nullable = false, unique = true)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String data;

    @OneToOne()
    @JoinColumn(name = "tagExamples_id")
    private TagExamplesEntity tagExamplesEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public TagExamplesEntity getTagExamplesEntity() {
        return tagExamplesEntity;
    }

    public void setTagExamplesEntity(TagExamplesEntity tagExamplesEntity) {
        this.tagExamplesEntity = tagExamplesEntity;
    }
}
