package com.example.shortURL.controller;

import com.example.shortURL.dto.UrlCreateRequestDto;
import com.example.shortURL.service.UrlManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mainController {
    UrlManager urlManager = new UrlManager();

    @PostMapping("/makeKey")
    public String makeKey(@RequestBody UrlCreateRequestDto urlCreateRequestDto) {
        return urlManager.makeUrl(urlCreateRequestDto.getOriginUrl());
    }
}
