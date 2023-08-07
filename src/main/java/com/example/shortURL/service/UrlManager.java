package com.example.shortURL.service;

import com.example.shortURL.domain.URL;
import com.example.shortURL.dto.UrlCreateResponseDto;
import com.example.shortURL.repository.URLs;
import org.springframework.stereotype.Service;

@Service
public class UrlManager {
    private URL newUrl;
    private URLs urls;
    private final KeyManager keyManager = new RandomKeyManager();

    public UrlCreateResponseDto makeUrl(String input) {
        this.newUrl = new URL(input, makeKey());
        return new UrlCreateResponseDto(newUrl.getNewUrl());
    }

    private String makeKey() {
        keyManager.makeKey();
        return keyManager.getKey();
    }
}
