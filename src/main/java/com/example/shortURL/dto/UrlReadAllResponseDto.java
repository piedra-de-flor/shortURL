package com.example.shortURL.dto;

import com.example.shortURL.domain.Url;

import java.util.List;

public class UrlReadAllResponseDto {
    private final List<Url> urls;

    public UrlReadAllResponseDto(List<Url> urls) {
        this.urls = urls;
    }

    public List<Url> getUrls() {
        return urls;
    }
}
