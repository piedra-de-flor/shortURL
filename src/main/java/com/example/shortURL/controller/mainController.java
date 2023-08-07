package com.example.shortURL.controller;

import com.example.shortURL.service.UrlManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mainController {
    @GetMapping("/makeKey")
    public String makeKey() {
        UrlManager urlManager = new UrlManager();
        return urlManager.makeUrl("");
    }
}
