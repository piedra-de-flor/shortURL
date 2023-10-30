package com.example.shortURL.vo;

public class UrlBaseProtocol {
    private final String BASE_PROTOCOL = "http://";
    private final String BASE_PROTOCOL_SECURE = "https://";
    private final String WORLD_WIDE_WEB = "www.";
    private final String originUrlWithBaseProtocol;

    public UrlBaseProtocol(String originUrl) {
        String result = originUrl;

        if (!isStartWithHttpProtocol(originUrl)) {
            result = BASE_PROTOCOL + originUrl;
        }

        this.originUrlWithBaseProtocol = changeUrlBaseToLowerCase(plusW3(result));
    }

    public String getOriginUrlWithBaseProtocol() {
        return originUrlWithBaseProtocol;
    }

    private boolean isStartWithHttpProtocol(String originUrl) {
        return originUrl.toLowerCase().startsWith(BASE_PROTOCOL) || originUrl.toLowerCase().startsWith(BASE_PROTOCOL_SECURE);
    }

    private String plusW3(String originUrl) {
        String originDomainName = null;
                
        if (originUrl.startsWith(BASE_PROTOCOL)) {
            originDomainName = originUrl.substring(UrlIndexValue.END_INDEX_OF_URL_BASE_PROTOCOL.index);    
        }
        
        if (originUrl.startsWith(BASE_PROTOCOL_SECURE)) {
            originDomainName = originUrl.substring(UrlIndexValue.END_INDEX_OF_URL_BASE_PROTOCOL_SECURE.index);
        }

        String domainNameLowerCase = originDomainName.toLowerCase();

        if (domainNameLowerCase.startsWith(WORLD_WIDE_WEB)) {
            return BASE_PROTOCOL + originDomainName;
        }

        return BASE_PROTOCOL + WORLD_WIDE_WEB + originDomainName;
    }

    private String changeUrlBaseToLowerCase(String originUrl) {
        String urlBase = originUrl.substring(UrlIndexValue.START_INDEX_OF_URL.index, UrlIndexValue.END_INDEX_BEFORE_DOMAIN_NAME.index);
        String urlDomainName = originUrl.substring(UrlIndexValue.END_INDEX_BEFORE_DOMAIN_NAME.index);

        return urlBase.toLowerCase() + urlDomainName;
    }
}
