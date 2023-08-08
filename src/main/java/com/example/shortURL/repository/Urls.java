package com.example.shortURL.repository;

import com.example.shortURL.domain.Url;

import java.util.List;

public class Urls implements Repository {
    private final List<Url> urls;

    public Urls(List<Url> urls) {
        this.urls = urls;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void saveUrl(Url url) {
        urls.add(url);
    }

    @Override
    public boolean validateDuplication(String newKey) {
        return false;
    }
}
