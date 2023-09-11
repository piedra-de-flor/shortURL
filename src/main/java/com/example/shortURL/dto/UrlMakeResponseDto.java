package com.example.shortURL.dto;

import com.example.shortURL.domain.Url;

public class UrlMakeResponseDto {
    private final String newUrl;

    public UrlMakeResponseDto(Url url) {
        this.newUrl = url.getNewUrl();
    }

    public String getNewUrl() {
        return newUrl;
    }
}
