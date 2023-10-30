package com.example.shortURL.vo;

public class NewUrl {
    private final BaseDomainName baseDomainName = new BaseDomainName();
    private final String newUrl;

    public NewUrl(String newUrl) {
        this.newUrl = plusBaseDomain(newUrl);
    }

    public String getNewUrl() {
        return this.newUrl;
    }

    public String getKey() {
        return newUrl.substring(baseDomainName.getDomain().length());
    }

    private String plusBaseDomain(String newUrl) {
        if (newUrl.startsWith(baseDomainName.getDomain())) {
            return newUrl;
        }
        return baseDomainName.getDomain() + newUrl;
    }
}
