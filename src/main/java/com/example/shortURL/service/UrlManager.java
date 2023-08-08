package com.example.shortURL.service;

import com.example.shortURL.domain.Url;
import com.example.shortURL.repository.Repository;
import com.example.shortURL.repository.UrlsCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UrlManager {
    private final String BASE_URL = "localhost:8080/";
    private Url newUrl;
    private final Repository urls;
    private final KeyManager keyManager;

    @Autowired
    public UrlManager() {
        this.urls = new UrlsCollection(new ArrayList<>());
        keyManager = new RandomKeyManager();
    }

    public String makeUrl(String input) {
        this.newUrl = new Url(input, makeKey());

        return BASE_URL + newUrl.getNewUrl();
    }

    private String makeKey() {
        String key;

        do {
            keyManager.makeKey();
            key = keyManager.getKey();
        } while (urls.validateDuplication(key));

        return key;
    }

    public String saveUrl(Url url) {
        urls.save(url);
        return "저장 성공";
    }

    public List<Url> readAll() {
        return urls.findAll();
    }

    public Url readByNewUrl(String newUrl) {
        return urls.findByNewUrl(newUrl);
    }

    public List<Url> readByOriginUrl(String originUrl) {
        return urls.findByOriginUrl(originUrl);
    }

    public void updateUrl(Url updateUrl) {
        urls.update(updateUrl);
    }

    public void deleteUrl(String targetUrl) {
        urls.delete(targetUrl);
    }
}
