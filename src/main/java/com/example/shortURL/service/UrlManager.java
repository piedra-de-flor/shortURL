package com.example.shortURL.service;

import com.example.shortURL.domain.URL;
import com.example.shortURL.repository.URLs;
import org.springframework.stereotype.Service;

@Service
public class UrlManager {
    private URL newUrl;
    private String originUrl;
    private URLs urls;
    private final KeyManager keyManager = new RandomKeyManager();

    public String makeUrl(String input) {
        return makeKey();
    }

    private String makeKey() {
        keyManager.makeKey();
        return keyManager.getKey();
    }
}
