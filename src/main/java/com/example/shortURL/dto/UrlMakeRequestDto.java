package com.example.shortURL.dto;

public class UrlMakeRequestDto {
    private final String originUrl;

    public UrlMakeRequestDto(String input) {
        this.originUrl = input;
    }

    public String getOriginUrl() {
        return originUrl;
    }
}
