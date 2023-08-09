package com.example.shortURL.domain;

public class OriginUrl {
    private final int START_INDEX_OF_URLS_BASE = 0;
    private final int END_INDEX_OF_URLS_BASE = 7;
    private final String BASE_HTTP_PROTOCOL = "http://";
    private final String originUrl;

    OriginUrl(String newUrl) {
        this.originUrl = changeToUrlForm(newUrl);
    }

    public String getOriginUrl() {
        return this.originUrl;
    }

    private String changeToUrlForm(String originUrl) {
        if (validateUrlForm(originUrl)) {
            return originUrl;
        } else {
            return BASE_HTTP_PROTOCOL + originUrl;
        }
    }

    private boolean validateUrlForm(String originUrl) {
        return originUrl.substring(START_INDEX_OF_URLS_BASE, END_INDEX_OF_URLS_BASE).equals(BASE_HTTP_PROTOCOL);
    }

}
