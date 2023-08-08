package com.example.shortURL.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class NewUrl {
    @Id
    private final String newUrl;

    NewUrl(String newUrl) {
        this.newUrl = newUrl;
    }

    public String getNewUrl() {
        return this.newUrl;
    }
}
