package com.example.shortURL.repository;

import com.example.shortURL.domain.Url;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UrlsCollection implements Repository {
    private final List<Url> urls;

    public UrlsCollection(List<Url> urls) {
        this.urls = urls;
    }

    @Override
    public boolean validateDuplication(String newKey) {
        return false;
    }

    @Override
    public List<Url> findAll() {
        return urls;
    }

    @Override
    public Url findByNewUrl(String newUrl) {
        Optional<Url> result = urls.stream()
                .filter(url -> url.getNewUrl().equals(newUrl))
                .findAny();

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new IllegalArgumentException("there is no url");
        }
    }

    @Override
    public List<Url> findByOriginUrl(String originUrl) {
        return urls.stream()
                .filter(url -> url.getNewUrl().equals(originUrl))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Url url) {
        urls.add(url);
    }

    @Override
    public void update(Url updateUrl) {
        Url target = findByNewUrl(updateUrl.getNewUrl());
        int targetIndex = urls.indexOf(target);
        urls.set(targetIndex, updateUrl);
    }

    @Override
    public void delete(String removeUrl) {
        Url target = findByNewUrl(removeUrl);
        urls.remove(target);
    }
}
