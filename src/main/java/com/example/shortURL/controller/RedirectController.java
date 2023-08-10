package com.example.shortURL.controller;

import com.example.shortURL.domain.Url;
import jakarta.servlet.http.HttpServletResponse;
import com.example.shortURL.service.UrlManager;
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
    @GetMapping("/{newUrl}")
    public void redirectUrl(@PathVariable String newUrl, HttpServletResponse httpServletResponse) throws IOException {
        Url url = urlManager.readByNewUrl(newUrl);
        url.plusCallCount();
        String targetUrl = url.getOriginUrl();
        httpServletResponse.sendRedirect(targetUrl);
    }
}
