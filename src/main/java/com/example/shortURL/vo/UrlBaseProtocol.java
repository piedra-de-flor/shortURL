package com.example.shortURL.vo;

public class UrlBaseProtocol {
    private final String BASE_PROTOCOL = "http://";
    private final String WORLD_WIDE_WEB = "www.";
    private final String originUrlWithBaseProtocol;

    public UrlBaseProtocol(String originUrl) {
        String result = originUrl;

        if (!isStartWithHttpProtocol(originUrl)) {
            result = BASE_PROTOCOL + originUrl;
        }

        this.originUrlWithBaseProtocol = plusW3(result);
    }

    public String getOriginUrlWithBaseProtocol() {
        return originUrlWithBaseProtocol;
    }

    private boolean isStartWithHttpProtocol(String originUrl) {
        return originUrl.toLowerCase().startsWith(BASE_PROTOCOL);
    }

    private String plusW3(String originUrl) {
        String originDomainName = originUrl.substring(UrlIndexValue.END_INDEX_OF_URL_BASE_PROTOCOL.index);
        String domainNameLowerCase = originDomainName.toLowerCase();

        if (domainNameLowerCase.startsWith(WORLD_WIDE_WEB)) {
            return BASE_PROTOCOL + originDomainName;
        }

        return BASE_PROTOCOL + WORLD_WIDE_WEB + originDomainName;
    }
}