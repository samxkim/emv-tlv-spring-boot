package com.omni.webapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class Tag {
    @Id
    private Integer id;

    private String name;

    private String description;

    private LocalDateTime created_date;

    private String created_by;

    private String update_date;

    private Boolean isactive;

    @OneToOne
    private TagExamplesEntity tagexamples_id_fk;

    @ManyToOne
    private TagGroupsEntity idtaggroups_id_fk;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
