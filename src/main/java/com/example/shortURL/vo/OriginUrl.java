package com.example.shortURL.vo;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class OriginUrl {
    private static final int HTTP_STATUS_CODE_OK = 200;
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
        return urlValidator.isValid(url) && isUrlExists(url);
    }

    public boolean isUrlExists(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);

        httpGet.addHeader("User-Agent", "test");
        httpGet.addHeader("Content-type", "application/json");

        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            int code = httpResponse.getStatusLine().getStatusCode();
            httpClient.close();

            if (code != HTTP_STATUS_CODE_OK) {
                return false;
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("http connection fail");
        }
        return true;
    }
}

