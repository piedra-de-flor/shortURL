package com.example.shortURL.service;

import com.example.shortURL.domain.Url;
import com.example.shortURL.repository.Repository;
import com.example.shortURL.vo.NewUrl;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UrlRedirectService {
    private final Logger log;

    public UrlRedirectService(Logger log) {
        this.log = log;
    }
    public void redirect(String newUrl, HttpServletResponse httpServletResponse, Repository urls, KeyMaker keyMaker) {
        try {
            String encodingKey = new NewUrl(newUrl).getKey();
            Url target = urls.findById(keyMaker.decodeKey(encodingKey));
            target.plusCallCount();
            httpServletResponse.sendRedirect(target.getOriginUrl());
        } catch (IOException e) {
            log.error("error log = {}", "redirect error");
            throw new IllegalArgumentException();
        }
    }
}
