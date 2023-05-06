package com.sr.chatpanel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class Site {
    @Id
    private Long id;

    @Column(name = "url")
    private String url;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
