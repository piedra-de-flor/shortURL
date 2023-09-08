package com.example.shortURL.dto;

import com.example.shortURL.domain.Url;

public class UrlResponseDto {
    private final String originUrl;
    private final String newUrl;

    public UrlResponseDto(Url url) {
        this.originUrl = url.getOriginUrl();
        this.newUrl = url.getNewUrl();
    }
}
