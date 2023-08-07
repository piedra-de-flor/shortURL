package com.example.shortURL.dto;

public class UrlCreateResponseDto {
    private final String BASE_URL = "localhost:8080/";
    private final String newUrl;

    public UrlCreateResponseDto(String newUrl) {
        this.newUrl = BASE_URL + newUrl;
    }

    public String getNewUrl() {
        return this.newUrl;
    }
}
