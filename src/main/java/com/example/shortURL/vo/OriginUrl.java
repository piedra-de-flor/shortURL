package com.example.shortURL.vo;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class OriginUrl {
    private final String originUrl;

    public OriginUrl(String originUrl) {
        this.originUrl = validateUrl(originUrl);
    }

    public String getOriginUrl() {
        return this.originUrl;
    }

    private String validateUrl(String originUrl) {
        String candidateUrl = normalizeUrlForm(originUrl);

        if (!isValidUrl(candidateUrl)) {
            throw new IllegalArgumentException("originUrl status error");
        }

        return candidateUrl;
    }

    private String normalizeUrlForm(String originUrl) {
        UrlBaseProtocol urlBaseProtocol = new UrlBaseProtocol(originUrl);
        return urlBaseProtocol.getOriginUrlWithBaseProtocol();
    }

    private boolean isValidUrl(String url) {
        String[] schemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(url) && isValidConnection(url);
    }

    private boolean isValidConnection(String url) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            HttpStatusCode httpStatus = responseEntity.getStatusCode();
            return httpStatus.is2xxSuccessful();
        } catch (HttpStatusCodeException e) {
            return false;
        }
    }
}

