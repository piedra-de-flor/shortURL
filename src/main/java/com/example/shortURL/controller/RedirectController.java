package com.example.shortURL.controller;

import com.example.shortURL.service.UrlRedirectManager;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class RedirectController {
    private final UrlRedirectManager redirectManager;

    @Autowired
    public RedirectController(UrlRedirectManager redirectManager) {
        this.redirectManager = redirectManager;
    }
    @GetMapping("/{newUrl}")
    public void redirectUrl(@PathVariable String newUrl, HttpServletResponse httpServletResponse) throws IOException {
        redirectManager.redirect(newUrl, httpServletResponse);
    }
}
