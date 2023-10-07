package com.example.shortURL.vo;

import org.apache.commons.validator.routines.UrlValidator;

public class OriginUrl {
    private static final int HTTP_STATUS_CODE_OK = 200;
    private static final int HTTP_STATUS_CODE_ERROR = 400;
    private static final int HTTP_STATUS_CODE_INDEX = 0;
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
        return urlValidator.isValid(url); //&& isUrlExists(url);
    }

    /*public boolean isUrlExists(String url) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = restTemplate.execute(url, HttpMethod.HEAD, null,
                (ResponseExtractor<HttpHeaders>) HttpMessage::getHeaders);

        List<String> httpStatusCode = headers.get("HttpStatus");
        String statusValue = httpStatusCode.get(HTTP_STATUS_CODE_INDEX);
        int statusCode = Integer.parseInt(statusValue);

        return statusCode >= HTTP_STATUS_CODE_OK && statusCode < HTTP_STATUS_CODE_ERROR;
    }*/
}

