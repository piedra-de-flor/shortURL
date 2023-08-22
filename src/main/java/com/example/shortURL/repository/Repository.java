package com.example.shortURL.repository;

import com.example.shortURL.domain.Url;

import java.util.List;

public interface Repository {
    boolean validateDuplication(String newKey);
    List<Url> findAll();
    Url findByNewUrl(String newUrl);

    Url findByOriginUrl(String originUrl);

    void save(Url url);
    void update(Url url);
    void delete(String url);
    int getDataSize();
}
