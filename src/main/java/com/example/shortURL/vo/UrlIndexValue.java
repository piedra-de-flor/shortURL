package com.example.shortURL.vo;

public enum UrlIndexValue {
    START_INDEX_OF_URL(0),
    END_INDEX_BEFORE_DOMAIN_NAME(11),
    END_INDEX_OF_URL_BASE_PROTOCOL(7);

    final int index;

    UrlIndexValue(int i) {
        this.index = i;
    }
}
