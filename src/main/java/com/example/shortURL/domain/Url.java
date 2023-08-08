package com.example.shortURL.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class Url {
    private final int INITIAL_CALL_COUNT = 0;
    private final NewUrl newUrl;
    private final String originUrl;
    private LocalDateTime deleteDate;
    private int callCount;

    @Autowired
    public Url(String input, String newUrl) {
        this.originUrl = input;
        this.newUrl = new NewUrl(newUrl);
        this.callCount = INITIAL_CALL_COUNT;
    }

    @Autowired
    public Url(String originUrl, NewUrl newUrl, LocalDateTime deleteDate, int callCount) {
        this.originUrl = originUrl;
        this.newUrl = newUrl;
        this.deleteDate = deleteDate;
        this.callCount = callCount;
    }

    public String getNewUrl() {
        return newUrl.getNewUrl();
    }

    public String getOriginUrl() {
        return originUrl;
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
