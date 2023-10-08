package com.example.shortURL.dto;

import com.example.shortURL.domain.Url;

import java.time.LocalDateTime;

public class UrlResponseDto {
    private final String originUrl;
    private final String newUrl;
    private final LocalDateTime time;
    private final int callCount;

    public UrlResponseDto(Url url) {
        this.originUrl = url.getOriginUrl();
        this.newUrl = url.getNewUrl();
        this.time = url.getDeleteDate();
        this.callCount = url.getCallCount();
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public String getNewUrl() {
        return newUrl;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public int getCallCount() {
        return callCount;
    }
}
