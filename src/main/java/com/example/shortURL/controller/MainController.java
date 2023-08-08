package com.example.shortURL.controller;

import com.example.shortURL.domain.Url;
import com.example.shortURL.service.UrlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
    private final UrlManager urlManager;

    @Autowired
    public MainController(UrlManager urlManager) {
        this.urlManager = urlManager;
    }

    @PostMapping("/makeUrl")
    public String makeUrl(@RequestBody String originUrl) {
        return urlManager.makeUrl(originUrl);
    }

    @PostMapping("/saveUrl")
    public String saveUrl(@RequestBody Url saveUrl) {
        return urlManager.saveUrl(saveUrl);
    }

    @PostMapping("/updateUrl")
    public void updateUrl(@RequestBody Url updateUrl) {
        urlManager.updateUrl(updateUrl);
    }

    @GetMapping("/readAll")
    public List<Url> readAllUrl() {
        return urlManager.readAll();
    }

    @GetMapping("/readByNewUrl/{url}")
    public Url readByNewUrl(@PathVariable String newUrl) {
        return urlManager.readByNewUrl(newUrl);
    }

    @PostMapping("/delete")
    public void deleteUrl(@RequestBody String newUrl) {
        urlManager.deleteUrl(newUrl);
    }
}
