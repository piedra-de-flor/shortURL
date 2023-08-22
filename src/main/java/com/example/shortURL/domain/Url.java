package com.example.shortURL.domain;

import com.example.shortURL.vo.UrlFields;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Url {
    @Id
    private final String originUrl;
    private final String newUrl;
    private LocalDateTime deleteDate;
    private int callCount;

    public Url(String originUrl, String newKey) {
        this.newUrl = newKey;
        this.originUrl = originUrl;
        this.callCount = UrlFields.INITIAL_CALL_COUNT.value;
        setDeleteDate();
    }

    public String getOriginUrl() {
        return this.originUrl;
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

    public void setDeleteDate() {
        LocalDateTime now = LocalDateTime.now();
        this.deleteDate = now.plusMonths(UrlFields.ONE_MONTH.value);
    }

    public void plusCallCount() {
        this.callCount++;
    }
}
