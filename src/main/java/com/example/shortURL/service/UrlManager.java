package com.example.shortURL.service;

import com.example.shortURL.domain.Url;
import com.example.shortURL.dto.UrlCreateResponseDto;
import com.example.shortURL.repository.Repository;
import com.example.shortURL.repository.UrlsCollection;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UrlManager {
    private Url newUrl;
    private final Repository urls;
    private final KeyManager keyManager;

    public UrlManager() {
        this.urls = new UrlsCollection(new ArrayList<>());
        keyManager = new RandomKeyManager();
    }

    public UrlCreateResponseDto makeUrl(String input) {
        this.newUrl = new Url(input, makeKey());
        return new UrlCreateResponseDto(newUrl.getNewUrl());
    }

    private String makeKey() {
        String key;

        do {
            keyManager.makeKey();
            key = keyManager.getKey();
        } while (urls.validateDuplication(key));
    }

    public String saveUrl(Url url) {
        urls.save(url);
        return "저장 성공";
    }
}
