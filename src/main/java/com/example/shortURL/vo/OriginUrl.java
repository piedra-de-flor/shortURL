package com.example.shortURL.vo;

public class OriginUrl {
    private UrlBaseProtocol urlBaseProtocol;
    private final String originUrl;

    public OriginUrl(String originUrl) {
        this.originUrl = changeToUrlForm(originUrl);
    }

    public String getOriginUrl() {
        return this.originUrl;
    }

    private String changeToUrlForm(String originUrl) {
        urlBaseProtocol = new UrlBaseProtocol(originUrl);

        return changeUrlBaseToLowerCase(urlBaseProtocol.getOriginUrlWithBaseProtocol());
    }
    private String changeUrlBaseToLowerCase(String url) {
        String urlBase = url.substring(UrlIndexValue.START_INDEX_OF_URL.index, UrlIndexValue.END_INDEX_BEFORE_DOMAIN_NAME.index);
        String urlDomainName = url.substring(UrlIndexValue.END_INDEX_BEFORE_DOMAIN_NAME.index);

        return urlBase.toLowerCase() + urlDomainName;
    }
}
