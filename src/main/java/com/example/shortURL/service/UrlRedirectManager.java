package com.example.shortURL.service;

import com.example.shortURL.domain.Url;
import com.example.shortURL.repository.Repository;
import com.example.shortURL.vo.NewUrl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UrlRedirectManager {
    private final Repository urls;

    UrlRedirectManager(Repository urls) {
        this.urls = urls;
    }

    public void redirect(String newUrl, HttpServletResponse httpServletResponse) throws IOException {
        String input = new NewUrl(newUrl).getNewUrl();
        Url target = urls.findByNewUrl(input);
        target.plusCallCount();
        httpServletResponse.sendRedirect(target.getOriginUrl());
    }
}
