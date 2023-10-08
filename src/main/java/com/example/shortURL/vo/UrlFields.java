package com.example.shortURL.vo;

public enum UrlFields {
    ONE_MONTH(1),
    INITIAL_CALL_COUNT(0);

    public final int value;

    UrlFields(int value) {
        this.value = value;
    }
}
