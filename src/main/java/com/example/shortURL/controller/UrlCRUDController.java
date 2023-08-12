package com.example.shortURL.controller;

import com.example.shortURL.domain.Url;
import com.example.shortURL.service.UrlCRUDManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UrlCRUDController {
    private final UrlCRUDManager urlCRUDManager;

    @Autowired
    public UrlCRUDController(UrlCRUDManager urlCRUDManager) {
        this.urlCRUDManager = urlCRUDManager;
    }

    @PostMapping("/makeUrl")
    public String makeUrl(@RequestBody String originUrl) {
        return urlCRUDManager.makeUrl(originUrl).getNewUrl();
    }

    @PostMapping("/saveUrl")
    public String saveUrl(@RequestBody Url saveUrl) {
        return urlCRUDManager.saveUrl(saveUrl);
    }

    @PostMapping("/updateUrl")
    public Url updateUrl(@RequestBody String newUrl) {
        return urlCRUDManager.updateUrl(newUrl);
    }

    @GetMapping("/readAll")
    public List<Url> readAllUrl() {
        return urlCRUDManager.readAll();
    }

    @GetMapping("/readByNewUrl")
    public Url readByNewUrl(@RequestParam String url) {
        return urlCRUDManager.readByNewUrl(url);
    }

    @GetMapping("/readByOriginUrl")
    public Url readByOriginUrl(@RequestParam String url) {
        return urlCRUDManager.readByOriginUrl(url);
    }

    @PostMapping("/delete")
    public void deleteUrl(@RequestBody String newUrl) {
        urlCRUDManager.deleteUrl(newUrl);
    }

    @PostMapping("/deleteAll")
    public void deleteUrl() {
        urlCRUDManager.deleteAllUrl();
    }
}
