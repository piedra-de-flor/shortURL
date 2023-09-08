package com.example.shortURL.controller;

import com.example.shortURL.domain.Url;
import com.example.shortURL.dto.UrlMakeRequestDto;
import com.example.shortURL.dto.UrlMakeResponseDto;
import com.example.shortURL.dto.UrlReadRequestDto;
import com.example.shortURL.dto.UrlResponseDto;
import com.example.shortURL.service.UrlService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/makeUrl")
    public UrlMakeResponseDto makeUrl(@RequestBody UrlMakeRequestDto urlMakeRequestDto) {
        return urlService.makeUrl(urlMakeRequestDto);
    }

    @PostMapping("/saveUrl")
    public String saveUrl(@RequestBody Url saveUrl) {
        return urlService.saveUrl(saveUrl);
    }

    @PostMapping("/updateUrl")
    public UrlResponseDto updateUrl(@RequestBody String newUrl) {
        return urlService.updateUrl(newUrl);
    }

    @GetMapping("/readAll")
    public List<Url> readAllUrl() {
        return urlService.readAll();
    }

    @GetMapping("/readByNewUrl")
    public UrlResponseDto readByNewUrl(@RequestParam UrlReadRequestDto readRequestDto) {
        return urlService.readByNewUrl(readRequestDto);
    }

    @GetMapping("/readByOriginUrl")
    public UrlResponseDto readByOriginUrl(@RequestParam UrlReadRequestDto readRequestDto) {
        return urlService.readByOriginUrl(readRequestDto);
    }

    @PostMapping("/delete")
    public void deleteUrl(@RequestBody String originUrl) {
        urlService.deleteUrl(originUrl);
    }

    @PostMapping("/deleteAll")
    public void deleteUrl() {
        urlService.deleteAllUrl();
    }
}
