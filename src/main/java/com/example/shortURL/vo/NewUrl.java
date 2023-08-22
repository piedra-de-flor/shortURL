package com.example.shortURL.vo;

public class NewUrl {
    private final String baseDomainName = "localhost:8080/";
    private final String newUrl;

    public NewUrl(String newUrl) {
        this.newUrl = changeToUrlForm(newUrl);
    }

    public String getNewUrl() {
        return this.newUrl;
    }

    private String changeToUrlForm(String newUrl) {
        return baseDomainName + newUrl;
    }
}
