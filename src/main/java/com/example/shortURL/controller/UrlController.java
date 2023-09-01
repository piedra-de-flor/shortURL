package com.example.shortURL.controller;

import com.example.shortURL.domain.Url;
import com.example.shortURL.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/makeUrl")
    public String makeUrl(@RequestBody String originUrl) {
        return urlService.makeUrl(originUrl).getNewUrl();
    }

    @PostMapping("/saveUrl")
    public String saveUrl(@RequestBody Url saveUrl) {
        return urlService.saveUrl(saveUrl);
    }

    @PostMapping("/updateUrl")
    public Url updateUrl(@RequestBody String newUrl) {
        return urlService.updateUrl(newUrl);
    }

    @GetMapping("/readAll")
    public List<Url> readAllUrl() {
        return urlService.readAll();
    }

    @GetMapping("/readByNewUrl")
    public Url readByNewUrl(@RequestParam String url) {
        return urlService.readByNewUrl(url);
    }

    @GetMapping("/readByOriginUrl")
    public Url readByOriginUrl(@RequestParam String url) {
        return urlService.readByOriginUrl(url);
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
