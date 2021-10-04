package com.omni.webapp.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date created_date;

    @Column(name = "created_by")
    private String created_by;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date update_date;

    @Column(name = "isActive")
    private Boolean isActive;

    @OneToOne(mappedBy = "tagExamplesEntity")
    private TagExamplesEntity tagExamplesFK;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tagGroupsEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TagGroupsEntity> tagGroupsEntityList;

    public Tag(String name, String description, String created_by, Boolean isActive) {
        this.name = name;
        this.description = description;
        this.created_by = created_by;
        this.isActive = isActive;
    }

    public Tag(String name, String description) {
        this.name = name;
        this.description = description;
        this.created_by = "Admin";
        this.isActive = true;
    }

    public Tag() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public TagExamplesEntity getTagExamplesFK() {
        return tagExamplesFK;
    }

    public void setTagExamplesFK(TagExamplesEntity tagExamplesFK) {
        this.tagExamplesFK = tagExamplesFK;
    }

    public List<TagGroupsEntity> getTagGroupsEntityList() {
        return tagGroupsEntityList;
    }

    public void setTagGroupsEntityList(List<TagGroupsEntity> tagGroupsEntityList) {
        this.tagGroupsEntityList = tagGroupsEntityList;
    }
}
