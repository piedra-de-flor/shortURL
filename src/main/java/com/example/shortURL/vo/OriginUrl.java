package com.example.shortURL.vo;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class OriginUrl {
    private final String originUrl;

    public OriginUrl(String originUrl) {
        this.originUrl = validateUrl(originUrl);
    }

    public String getOriginUrl() {
        return this.originUrl;
    }

    private String validateUrl(String originUrl) {
        String candidateUrl = normalizeUrlForm(originUrl);

        if (!isValidUrl(candidateUrl)) {
            throw new IllegalArgumentException("originUrl status error");
        }

        return candidateUrl;
    }

    private String normalizeUrlForm(String originUrl) {
        UrlBaseProtocol urlBaseProtocol = new UrlBaseProtocol(originUrl);
        return urlBaseProtocol.getOriginUrlWithBaseProtocol();
    }

    private boolean isValidUrl(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (URISyntaxException | MalformedURLException exception) {
            return false;
        }
    }
}

