package com.example.shortURL.service;

import com.example.shortURL.domain.Url;
import com.example.shortURL.dto.UrlMakeRequestDto;
import com.example.shortURL.dto.UrlMakeResponseDto;
import com.example.shortURL.dto.UrlReadRequestDto;
import com.example.shortURL.dto.UrlResponseDto;
import com.example.shortURL.repository.Repository;
import com.example.shortURL.repository.RepositoryImpl;
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
public class UrlService {
    private final Repository repository;
    private final KeyMaker keyMaker;

    public UrlService(KeyMaker keyMaker, LogVO logVO) {
        this.repository = new RepositoryImpl(new ArrayList<>(), logVO.getLog());
        this.keyMaker = keyMaker;
    }

    public Repository getDB() {
        return repository;
    }

    private String NormalizeOriginUrlForm(String input) {
        OriginUrl originUrlVO = new OriginUrl(input);

        return originUrlVO.getOriginUrl();
    }

    @Cacheable(key = "#originUrl")
    public UrlMakeResponseDto makeUrl(UrlMakeRequestDto urlMakeRequestDto) {
        String originUrl = NormalizeOriginUrlForm(urlMakeRequestDto.getOriginUrl());

        try {
            Url urlData = repository.findByOriginUrl(originUrl);
            updateUrl(urlData.getOriginUrl());

            return new UrlMakeResponseDto(urlData);
        } catch (IllegalArgumentException e) {
            Url newUrl = new Url(originUrl, makeKey());
            saveUrl(newUrl);

            return new UrlMakeResponseDto(newUrl);
        }
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


    public UrlResponseDto readByNewUrl(UrlReadRequestDto readRequestDto) {
        NewUrl newUrl = new NewUrl(readRequestDto.getInput());
        String target = newUrl.getNewUrl();
        return new UrlResponseDto(repository.findByNewUrl(target));
    }

    @Cacheable(key = "#originUrl")
    public UrlResponseDto readByOriginUrl(UrlReadRequestDto readRequestDto) {
        String target = NormalizeOriginUrlForm(readRequestDto.getInput());
        return new UrlResponseDto(repository.findByOriginUrl(target));
    }

    @CachePut(key = "#originUrl")
    public UrlResponseDto updateUrl(String originUrl) {
        Url updateUrl = repository.findByOriginUrl(NormalizeOriginUrlForm(originUrl));
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
