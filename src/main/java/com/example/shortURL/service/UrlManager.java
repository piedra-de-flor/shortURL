package com.example.shortURL.service;

import com.example.shortURL.domain.Url;
import com.example.shortURL.dto.UrlCreateResponseDto;
import com.example.shortURL.repository.Urls;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UrlManager {
    private Url newUrl;
    private final Urls urls;
    private final KeyManager keyManager = new RandomKeyManager();

    public UrlManager() {
        this.urls = new Urls(new ArrayList<>());
    }

    public UrlCreateResponseDto makeUrl(String input) {
        this.newUrl = new Url(input, makeKey());
        return new UrlCreateResponseDto(newUrl.getNewUrl());
    }

    private String makeKey() {
        keyManager.makeKey();
        return keyManager.getKey();
    }

    public String saveUrl(Url url) {
        urls.saveUrl(url);
        return "저장 성공";
    }
}
