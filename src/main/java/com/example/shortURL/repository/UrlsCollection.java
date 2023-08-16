package com.example.shortURL.repository;

import com.example.shortURL.domain.Url;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
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

        return checkFindUrlIsPresent(result);
    }

    @Override
    public Url findByOriginUrl(String originUrl) {
        Optional<Url> result = urls.stream()
                .filter(url -> url.getOriginUrl().equals(originUrl))
                .findAny();

        return checkFindUrlIsPresent(result);
    }

    private Url checkFindUrlIsPresent(Optional<Url> resultForFind) {
        if (resultForFind.isPresent()) {
            return resultForFind.get();
        }
        throw new IllegalArgumentException("there is no Url");
    }

    @Override
    public void save(Url url) {
        urls.add(url);
    }

    @Override
    public void update(Url updateUrl) {
        Url target = findByNewUrl(updateUrl.getNewUrl());
        int targetIndex = urls.indexOf(target);
        updateUrl.setDeleteDate();
        urls.set(targetIndex, updateUrl);
    }

    @Override
    public void delete(String removeUrl) {
        Url target = findByNewUrl(removeUrl);
        urls.remove(target);
    }
}
