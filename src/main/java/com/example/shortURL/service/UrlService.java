package com.example.shortURL.service;

import com.example.shortURL.domain.Url;
import com.example.shortURL.dto.*;
import com.example.shortURL.repository.Repository;
import com.example.shortURL.repository.RepositoryImpl;
import com.example.shortURL.vo.NewUrl;
import com.example.shortURL.vo.OriginUrl;
import org.slf4j.Logger;
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
public class UrlService {
    private final Repository repository;
    private final KeyMaker keyMaker;

    public UrlService(KeyMaker keyMaker, Logger log) {
        this.repository = new RepositoryImpl(new ArrayList<>(), log);
        this.keyMaker = keyMaker;
    }

    public Repository getDB() {
        return repository;
    }

    private String normalizeOriginUrl(String url) {
        OriginUrl originUrl = new OriginUrl(url);
        return originUrl.getOriginUrl();
    }

    @Cacheable(key = "#urlMakeRequestDto.originUrl")
    public UrlMakeResponseDto makeUrl(UrlMakeRequestDto urlMakeRequestDto) {
        String originUrl = normalizeOriginUrl(urlMakeRequestDto.getOriginUrl());
        UrlMakeResponseDto urlMakeResponseDto = new UrlMakeResponseDto(makeNewUrl(originUrl));

        if (repository.isExist(originUrl)) {
            Url urlData = repository.findByOriginUrl(originUrl);
            updateUrl(urlData.getOriginUrl());

            urlMakeResponseDto = new UrlMakeResponseDto(urlData);
        }

        return urlMakeResponseDto;
    }

    private Url makeNewUrl(String originUrl) {
        Url newUrl = new Url(originUrl, makeKey());
        saveUrl(newUrl);

        return newUrl;
    }

    private String makeKey() {
        String key;

        do {
            keyMaker.makeKey();
            key = keyMaker.getKey();
        } while (repository.validateDuplication(key));

        return new NewUrl(key).getNewUrl();
    }

    public String saveUrl(Url url) {
        repository.save(url);

        return "저장 성공";
    }

    public List<Url> readAll() {
        return repository.findAll();
    }


    public UrlResponseDto readByNewUrl(UrlReadByNewUrlRequestDto readRequestDto) {
        NewUrl newUrl = new NewUrl(readRequestDto.getNewUrl());
        String target = newUrl.getNewUrl();
        return new UrlResponseDto(repository.findByNewUrl(target));
    }

    @Cacheable(key = "#readRequestDto.input")
    public UrlResponseDto readByOriginUrl(UrlReadByOriginUrlRequestDto readRequestDto) {
        String target = normalizeOriginUrl(readRequestDto.getOriginUrl());
        return new UrlResponseDto(repository.findByOriginUrl(target));
    }

    @CachePut(key = "#originUrl")
    public UrlResponseDto updateUrl(String originUrl) {
        Url updateUrl = repository.findByOriginUrl(originUrl);
        repository.update(updateUrl);

        return new UrlResponseDto(updateUrl);
    }

    @CacheEvict(key = "#originUrl", beforeInvocation = false)
    public void deleteUrl(String originUrl) {
        repository.delete(originUrl);
    }

    @CacheEvict(allEntries = true)
    public void deleteAllUrl() {
        List<Url> targetList = repository.findAll();
        targetList.removeAll(targetList);
    }
}
