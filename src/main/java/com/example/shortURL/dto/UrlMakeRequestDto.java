package com.example.shortURL.dto;

import com.example.shortURL.vo.OriginUrl;

public class UrlMakeRequestDto {
    private OriginUrl originUrl;

    public void setOriginUrl(String originUrl) {
        this.originUrl = new OriginUrl(originUrl);
    }

    public String getOriginUrl() {
        return originUrl.getOriginUrl();
    }
}
