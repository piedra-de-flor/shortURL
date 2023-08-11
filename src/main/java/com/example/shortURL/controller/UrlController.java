package com.example.shortURL.controller;

import com.example.shortURL.domain.Url;
import com.example.shortURL.service.UrlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UrlController {
    private final UrlManager urlManager;

    @Autowired
    public UrlController(UrlManager urlManager) {
        this.urlManager = urlManager;
    }

    @PostMapping("/makeUrl")
    public String makeUrl(@RequestBody String originUrl) {
        return urlManager.takeInput(originUrl).getNewUrl();
    }

    @PostMapping("/saveUrl")
    public String saveUrl(@RequestBody Url saveUrl) {
        return urlManager.saveUrl(saveUrl);
    }

    @PostMapping("/updateUrl")
    public Url updateUrl(@RequestBody String newUrl) {
        return urlManager.updateUrl(newUrl);
    }

    @GetMapping("/readAll")
    public List<Url> readAllUrl() {
        return urlManager.readAll();
    }

    @GetMapping("/readByNewUrl")
    public Url readByNewUrl(@RequestParam String url) {
        return urlManager.readByNewUrl(url);
    }

    @GetMapping("/readByOriginUrl")
    public Url readByOriginUrl(@RequestParam String url) {
        return urlManager.readByOriginUrl(url);
    }

    @PostMapping("/delete")
    public void deleteUrl(@RequestBody String newUrl) {
        urlManager.deleteUrl(newUrl);
    }
}
