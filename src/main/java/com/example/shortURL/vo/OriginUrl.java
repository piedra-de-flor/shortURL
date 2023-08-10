package com.example.shortURL.vo;

public class OriginUrl {
    private final int START_INDEX_OF_URL = 0;
    private final int END_INDEX_BEFORE_DOMAIN_NAME = 11;
    private final int END_INDEX_OF_URL_BASE_PROTOCOL = 7;
    private final String BASE_PROTOCOL = "http://";
    private final String WORLD_WIDE_WEB = "www.";
    private final String originUrl;

    public OriginUrl(String originUrl) {
        this.originUrl = changeToUrlForm(originUrl);
    }

    public String getOriginUrl() {
        return this.originUrl;
    }

    private String changeToUrlForm(String originUrl) {
        String result = originUrl;

        if (!isStartWithHttpProtocol(originUrl)) {
            result = BASE_PROTOCOL + originUrl;
        }

        return changeUrlBaseToLowerCase(plusW3(result));
    }

    private boolean isStartWithHttpProtocol(String originUrl) {
        return originUrl.toLowerCase().startsWith(BASE_PROTOCOL);
    }

    private String plusW3(String originUrl) {
            String originDomainName = originUrl.substring(END_INDEX_OF_URL_BASE_PROTOCOL);

            if (originDomainName.toLowerCase().startsWith(WORLD_WIDE_WEB)) {
                return BASE_PROTOCOL + originDomainName;
            } else {
                return BASE_PROTOCOL + WORLD_WIDE_WEB + originDomainName;
            }
    }

    private String changeUrlBaseToLowerCase(String url) {
        String urlBaseLowerCase = url.substring(START_INDEX_OF_URL, END_INDEX_BEFORE_DOMAIN_NAME).toLowerCase();
        return urlBaseLowerCase + url.substring(END_INDEX_BEFORE_DOMAIN_NAME);
    }
}
