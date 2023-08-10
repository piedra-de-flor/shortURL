package com.example.shortURL.service;

import com.example.shortURL.domain.Url;
import com.example.shortURL.repository.Repository;
import com.example.shortURL.repository.UrlsCollection;
import com.example.shortURL.vo.OriginUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "UrlCache")
public class UrlManager {
    private final Repository urls;
    private final KeyManager keyManager;

    @Autowired
    public UrlManager(RandomKeyManager randomKeyManager) {
        this.urls = new UrlsCollection(new ArrayList<>());
        this.keyManager = randomKeyManager;
    }

    public Url takeInput(String input) {
        OriginUrl originUrlVO = new OriginUrl(input);
        return makeUrl(originUrlVO.getOriginUrl());
    }

    @Cacheable(key = "#originUrl")
    private Url makeUrl(String originUrl) {
        try {
            return urls.findByOriginUrl(originUrl);
        } catch (IllegalArgumentException e) {
            Url newUrl = new Url(originUrl, makeKey());
            saveUrl(newUrl);
            return newUrl;
        }
    }

    private String makeKey() {
        String key;

        do {
            keyManager.makeKey();
            key = keyManager.getKey();
        } while (urls.validateDuplication(key));

        return key;
    }

    public String saveUrl(Url url) {
        urls.save(url);
        return "저장 성공";
    }

    public List<Url> readAll() {
        return urls.findAll();
    }

    @Cacheable(key = "#newUrl")
    public Url readByNewUrl(String newUrl) {
        return urls.findByNewUrl(newUrl);
    }

    @Cacheable(key = "#originUrl")
    public Url readByOriginUrl(String originUrl) {
        return urls.findByOriginUrl(originUrl);
    }

    @CachePut(key = "#originUrl")
    public Url updateUrl(String originUrl) {
        Url updateUrl = readByOriginUrl(originUrl);
        urls.update(updateUrl);
        return readByNewUrl(updateUrl.getNewUrl());
    }

    @CacheEvict(key = "#originUrl", beforeInvocation = false)
    public void deleteUrl(String originUrl) {
        urls.delete(originUrl);
    }
}
