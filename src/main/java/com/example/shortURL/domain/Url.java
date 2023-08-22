package com.example.shortURL.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Url {
    private final String BASE_URL = "localhost:8080/";
    private final int ONE_MONTH = 1;
    private final int INITIAL_CALL_COUNT = 0;
    @Id
    private final String originUrl;
    private final String newUrl;
    private LocalDateTime deleteDate;
    private int callCount;

    public Url(String originUrl, String newKey) {
        this.newUrl = BASE_URL + newKey;
        this.originUrl = originUrl;
        this.callCount = INITIAL_CALL_COUNT;
        setDeleteDate();
    }
    /*public Url() {
        this.newUrl = null;
        this.originUrl = null;
    }*/


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
        this.deleteDate = now.plusMonths(ONE_MONTH);
    }

    public void plusCallCount() {
        this.callCount++;
    }
}
