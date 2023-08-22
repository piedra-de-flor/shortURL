package com.example.shortURL.vo;

public class NewUrl {
    private final String baseDomainName = "localhost:8080/";
    private final String newUrl;

    public NewUrl(String newUrl) {
        this.newUrl = plusBaseDomain(newUrl);
    }

    public String getNewUrl() {
        return this.newUrl;
    }

    private String plusBaseDomain(String newUrl) {
        if (newUrl.startsWith(baseDomainName)) {
            return newUrl;
        }
        return baseDomainName + newUrl;
    }
}
