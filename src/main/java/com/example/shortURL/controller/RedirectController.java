package com.example.shortURL.controller;

import com.example.shortURL.service.UrlCRUDManager;
import com.example.shortURL.service.UrlRedirectManager;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RedirectController {
    private final UrlRedirectManager redirectManager;
    private final UrlCRUDManager urlCRUDManager;

    @Autowired
    public RedirectController(UrlRedirectManager redirectManager, UrlCRUDManager urlCRUDManager) {
        this.urlCRUDManager = urlCRUDManager;
        this.redirectManager = redirectManager;
    }
    @GetMapping("/{newUrl}")
    public void redirectUrl(@PathVariable String newUrl, HttpServletResponse httpServletResponse) {
        redirectManager.redirect(newUrl, httpServletResponse, urlCRUDManager.getDB());
    }
}
