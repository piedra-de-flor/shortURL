package com.example.shortURL.repository;

import com.example.shortURL.domain.Url;
import com.example.shortURL.vo.NewUrl;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RepositoryImpl implements Repository {
    private final Logger log;
    private final List<Url> urls;

    public RepositoryImpl(List<Url> urls, Logger log) {
        this.urls = urls;
        this.log = log;
    }

    @Override
    public boolean validateDuplication(String newKey) {
        try {
            findByNewUrl(new NewUrl(newKey).getNewUrl());
            log.info("info log = {}", "duplication Key");
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public List<Url> findAll() {
        return urls;
    }

    @Override
    public Url findByNewUrl(String newUrl) {
        Optional<Url> result = urls.stream()
                .filter(url -> url.getNewUrl().equals(newUrl))
                .findAny();

        return checkFindUrlIsPresent(result);
    }

    @Override
    public Url findByOriginUrl(String originUrl) {
        Optional<Url> result = urls.stream()
                .filter(url -> url.getOriginUrl().equals(originUrl))
                .findAny();

        return checkFindUrlIsPresent(result);
    }

    private Url checkFindUrlIsPresent(Optional<Url> resultForFind) {
        if (resultForFind.isPresent()) {
            return resultForFind.get();
        }
        log.warn("warn log = {}", "there is no equal data in DB");
        throw new IllegalArgumentException();
    }

    @Override
    public void save(Url url) {
        urls.add(url);
    }

    @Override
    public void update(Url updateUrl) {
        Url target = findByOriginUrl(updateUrl.getOriginUrl());
        int targetIndex = urls.indexOf(target);
        updateUrl.setDeleteDate();
        urls.set(targetIndex, updateUrl);
    }

    @Override
    public void delete(String removeUrl) {
        Url target = findByOriginUrl(removeUrl);
        urls.remove(target);
    }

    @Override
    public int getDataSize() {
        return urls.size();
    }
}
