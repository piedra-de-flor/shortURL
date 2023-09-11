package com.example.shortURL.dto;

public class UrlReadRequestDto {
    private final String input;

    public UrlReadRequestDto(String url) {
        this.input = url;
    }

    public String getInput() {
        return input;
    }
}
