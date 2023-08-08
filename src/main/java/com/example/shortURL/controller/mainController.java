package com.example.shortURL.controller;

import com.example.shortURL.domain.Url;
import com.example.shortURL.dto.UrlCreateRequestDto;
import com.example.shortURL.dto.UrlSaveRequestDto;
import com.example.shortURL.service.UrlManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mainController {
    UrlManager urlManager = new UrlManager();

    @PostMapping("/makeUrl")
    public String makeUrl(@RequestBody UrlCreateRequestDto urlCreateRequestDto) {
        return urlManager.makeUrl(urlCreateRequestDto.getOriginUrl()).getNewUrl();
    }

    @PostMapping("/saveUrl")
    public String saveUrl(@RequestBody UrlSaveRequestDto urlSaveRequestDto) {
        Url saveUrl = new Url(
                urlSaveRequestDto.getOriginUrl(),
                urlSaveRequestDto.getNewUrl(),
                urlSaveRequestDto.getDeleteDate(),
                urlSaveRequestDto.getCallCount()
        );

        return urlManager.saveUrl(saveUrl);
    }
}
