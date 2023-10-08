package com.example.shortURL.domain;

import com.example.shortURL.vo.UrlFields;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String originUrl;
    private String newUrl;
    private LocalDateTime deleteDate;
    private int callCount;

    public Url(String originUrl, String newKey) {
        this.newUrl = newKey;
        this.originUrl = originUrl;
        this.callCount = UrlFields.INITIAL_CALL_COUNT.value;
        setDeleteDate();
    }

    protected Url() {
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

    private void setDeleteDate() {
        LocalDateTime now = LocalDateTime.now();
        this.deleteDate = now.plusMonths(UrlFields.ONE_MONTH.value);
    }

    public int getCallCount() {
        return callCount;
    }

    public void plusCallCount() {
        this.callCount++;
    }
}
