package com.example.shortURL.dto;

public class UrlReadByOriginUrlRequestDto {
    private String originUrl;

    public void setOriginUrl(String url) {
        this.originUrl = url;
    }

    public String getOriginUrl() {
        return originUrl;
    }
}
