package com.example.shortURL.service;

import com.example.shortURL.domain.Url;
import com.example.shortURL.repository.Repository;
import com.example.shortURL.repository.Urls;
import com.example.shortURL.vo.LogVO;
import com.example.shortURL.vo.NewUrl;
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
public class UrlCRUDManager {
    private final Repository urls;
    private final KeyManager keyManager;

    @Autowired
    public UrlCRUDManager(KeyManager randomKeyManager, LogVO logVO) {
        this.urls = new Urls(new ArrayList<>(), logVO.getLog());
        this.keyManager = randomKeyManager;
    }

    public Repository getDB() {
        return urls;
    }

    private String changeInputForm(String input) {
        OriginUrl originUrlVO = new OriginUrl(input);

        return originUrlVO.getOriginUrl();
    }

    @Cacheable(key = "#originUrl")
    public Url makeUrl(String originUrl) {
        originUrl = changeInputForm(originUrl);

        try {
            Url urlData = urls.findByOriginUrl(originUrl);
            updateUrl(urlData.getOriginUrl());

            return urlData;
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

        return new NewUrl(key).getNewUrl();
    }

    public String saveUrl(Url url) {
        urls.save(url);

        return "저장 성공";
    }

    public List<Url> readAll() {
        return urls.findAll();
    }


    public Url readByNewUrl(String newUrl) {
        String target = new NewUrl(newUrl).getNewUrl();
        return urls.findByNewUrl(target);
    }

    @Cacheable(key = "#originUrl")
    public Url readByOriginUrl(String originUrl) {
        return urls.findByOriginUrl(changeInputForm(originUrl));
    }

    @CachePut(key = "#originUrl")
    public Url updateUrl(String originUrl) {
        Url updateUrl = readByOriginUrl(originUrl);
        urls.update(updateUrl);

        return updateUrl;
    }

    @CacheEvict(key = "#originUrl", beforeInvocation = false)
    public void deleteUrl(String originUrl) {
        urls.delete(originUrl);
    }

    @CacheEvict(allEntries = true)
    public void deleteAllUrl() {
        List<Url> targetList = urls.findAll();
        targetList.removeAll(targetList);
    }
}
