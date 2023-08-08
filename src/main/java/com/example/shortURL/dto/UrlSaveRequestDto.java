package com.example.shortURL.dto;

import com.example.shortURL.domain.NewUrl;

import java.time.LocalDateTime;

public class UrlSaveRequestDto {
    private String originUrl;
    private NewUrl newUrl;
    private LocalDateTime deleteDate;
    private int callCount;

    public String getOriginUrl() {
        return originUrl;
    }

    public NewUrl getNewUrl() {
        return newUrl;
    }

    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }

    public int getCallCount() {
        return callCount;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public void setNewUrl(NewUrl newUrl) {
        this.newUrl = newUrl;
    }

    public void setDeleteDate(LocalDateTime deleteDate) {
        this.deleteDate = deleteDate;
    }

    public void setCallCount(int callCount) {
        this.callCount = callCount;
    }
}
