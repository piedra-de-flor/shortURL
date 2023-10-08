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

        if (repository.isExist(originUrl)) {
            Url urlData = repository.findByOriginUrl(originUrl);
            UrlUpdateRequestDto urlUpdateRequestDto = new UrlUpdateRequestDto();
            urlUpdateRequestDto.setOriginUrl(urlData.getOriginUrl());
            updateUrl(urlUpdateRequestDto);

            return new UrlMakeResponseDto(urlData);
        }

        return new UrlMakeResponseDto(makeNewUrl(originUrl));
    }

    private Url makeNewUrl(String originUrl) {
        int id = repository.getId();

        Url newUrl = new Url(originUrl, makeKey(id));
        newUrl.setId(id);

        saveUrl(newUrl);

        return newUrl;
    }

    private String makeKey(int id) {
       return new NewUrl(keyMaker.makeKey(id)).getNewUrl();
    }

    public String saveUrl(Url url) {
        repository.save(url);

        return "저장 성공";
    }

    public UrlReadAllResponseDto readAll() {
        return new UrlReadAllResponseDto(repository.findAll());
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

    @CachePut(key = "#urlUpdateRequestDto.originUrl")
    public UrlResponseDto updateUrl(UrlUpdateRequestDto urlUpdateRequestDto) {
        Url updateUrl = repository.findByOriginUrl(urlUpdateRequestDto.getOriginUrl());
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
