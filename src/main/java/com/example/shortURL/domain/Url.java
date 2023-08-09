package com.example.shortURL.domain;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class Url {
    private final int INITIAL_CALL_COUNT = 0;
    private final OriginUrl originUrl;
    private final String newUrl;
    private LocalDateTime deleteDate;
    private int callCount;

    @Autowired
    public Url(String input, String newUrl) {
        this.newUrl = newUrl;
        this.originUrl = new OriginUrl(input);
        this.callCount = INITIAL_CALL_COUNT;
        setDeleteDate();
    }

    public String getOriginUrl() {
        return originUrl.getOriginUrl();
    }

    public String getNewUrl() {
        return newUrl;
    }

    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }

    public int getCallCount() {
        return callCount;
    }

    private void setDeleteDate() {
        LocalDateTime now = LocalDateTime.now();
        this.deleteDate = now.plusMonths(1);
    }

    public void plusCallCount() {
        this.callCount++;
    }
}
