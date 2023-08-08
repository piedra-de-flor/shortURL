package com.example.shortURL.service;

import com.example.shortURL.domain.URL;
import com.example.shortURL.dto.UrlCreateResponseDto;
import com.example.shortURL.repository.URLs;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UrlManager {
    private URL newUrl;
    private final URLs urls;
    private final KeyManager keyManager = new RandomKeyManager();

    public UrlManager() {
        this.urls = new URLs(new ArrayList<>());
    }

    public UrlCreateResponseDto makeUrl(String input) {
        this.newUrl = new URL(input, makeKey());
        return new UrlCreateResponseDto(newUrl.getNewUrl());
    }

    private String makeKey() {
        keyManager.makeKey();
        return keyManager.getKey();
    }

    public String saveUrl(URL url) {
        urls.saveUrl(url);
        return "저장 성공";
    }
}
