package com.example.shortURL.controller;

import com.example.shortURL.service.UrlRedirectService;
import com.example.shortURL.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RedirectController {
    private final UrlRedirectService redirectManager;
    private final UrlService urlService;

    public RedirectController(UrlRedirectService redirectManager, UrlService urlService) {
        this.urlService = urlService;
        this.redirectManager = redirectManager;
    }
    @GetMapping("/{newUrl}")
    public void redirectUrl(@PathVariable String newUrl, HttpServletResponse httpServletResponse) {
        redirectManager.redirect(newUrl, httpServletResponse, urlService.getDB(), urlService.getKeyMaker());
    }
}
