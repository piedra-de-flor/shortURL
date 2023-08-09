package com.example.shortURL.controller;

import com.example.shortURL.service.UrlManager;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class RedirectController {
    private final UrlManager urlManager;

    @Autowired
    public RedirectController(UrlManager urlManager) {
        this.urlManager = urlManager;
    }
    @GetMapping("/{url}")
    public void redirectUrl(@PathVariable String url, HttpServletResponse httpServletResponse) throws IOException {
        String originUrl = urlManager.readByNewUrl(url).getOriginUrl();
        httpServletResponse.sendRedirect(originUrl);
    }
}
