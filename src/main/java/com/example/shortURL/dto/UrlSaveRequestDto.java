package com.example.shortURL.dto;

import com.example.shortURL.domain.Url;

public class UrlSaveRequestDto {
    private Url url;

    public void setUrl(String newUrl, String originUrl) {
        this.url = new Url(newUrl, originUrl);
    }

    public Url getUrl() {
        return url;
    }
}
