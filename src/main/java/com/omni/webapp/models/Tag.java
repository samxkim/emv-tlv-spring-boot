package com.omni.webapp.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private long id;

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

    @OneToOne
    private TagExamplesEntity tagExamplesFK;

    @ManyToOne
    private TagGroupsEntity tagGroupsFK;

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
}
