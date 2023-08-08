package com.example.shortURL.repository;

public interface Repository {
    boolean validateDuplication(String newKey);
}
