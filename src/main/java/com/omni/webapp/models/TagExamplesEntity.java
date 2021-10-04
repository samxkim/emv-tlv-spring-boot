package com.omni.webapp.models;

import javax.persistence.*;

@Entity
public class TagExamplesEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String data;

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
}
