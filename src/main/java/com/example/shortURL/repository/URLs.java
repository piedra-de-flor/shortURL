package com.example.shortURL.repository;

import com.example.shortURL.domain.URL;

import java.util.List;

public class URLs implements Repository {
    private final List<URL> urls;

    public URLs(List<URL> urls) {
        this.urls = urls;
    }

    public List<URL> getUrls() {
        return urls;
    }

    public void saveUrl(URL url) {
        urls.add(url);
    }

    @Override
    public boolean validateDuplication(String newKey) {
        return false;
    }
}
