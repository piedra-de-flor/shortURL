package com.example.shortURL.domain;

import java.time.LocalDateTime;

public class URL {
    private final int INITIAL_CALL_COUNT = 0;
    private final String originUrl;
    private final NewUrl newUrl;
    private LocalDateTime deleteDate;
    private int callCount;

    public URL(String input, String newUrl) {
        this.originUrl = input;
        this.newUrl = new NewUrl(newUrl);
        this.callCount = INITIAL_CALL_COUNT;
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
