package com.example.shortURL.controller;

import com.example.shortURL.domain.Url;
import com.example.shortURL.dto.*;
import com.example.shortURL.service.UrlService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/originUrl")
    public UrlMakeResponseDto makeUrl(@RequestBody UrlMakeRequestDto urlMakeRequestDto) {
        return urlService.makeUrl(urlMakeRequestDto);
    }

    @PostMapping("/url")
    public String saveUrl(@RequestBody Url saveUrl) {
        return urlService.saveUrl(saveUrl);
    }

    @PutMapping("/originUrl")
    public UrlResponseDto updateUrl(@RequestBody UrlUpdateRequestDto urlUpdateRequestDto) {
        return urlService.updateUrl(urlUpdateRequestDto);
    }

    @GetMapping("/All")
    public UrlReadAllResponseDto readAllUrl() {
        return urlService.readAll();
    }

    @GetMapping("/NewUrl")
    public UrlResponseDto readByNewUrl(@RequestParam UrlReadByNewUrlRequestDto readRequestDto) {
        return urlService.readByNewUrl(readRequestDto);
    }

    @GetMapping("/OriginUrl")
    public UrlResponseDto readByOriginUrl(@RequestParam UrlReadByOriginUrlRequestDto readRequestDto) {
        return urlService.readByOriginUrl(readRequestDto);
    }

    @DeleteMapping("/originUrl")
    public void deleteUrl(@RequestBody String originUrl) {
        urlService.deleteUrl(originUrl);
    }

    @DeleteMapping("/All")
    public void deleteUrl() {
        urlService.deleteAllUrl();
    }
}
