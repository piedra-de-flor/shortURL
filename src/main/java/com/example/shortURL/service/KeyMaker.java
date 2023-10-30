package com.example.shortURL.service;

public interface KeyMaker {
    String makeKey(int id);
    int decodeKey(String newUrl);
}
