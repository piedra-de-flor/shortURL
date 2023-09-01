package com.example.shortURL;

import com.example.shortURL.service.KeyMaker;

public class KeyMakerForTest implements KeyMaker {
    String testKey;

    @Override
    public void makeKey() {
        testKey = "testKey";
    }

    @Override
    public void makeKey(String input) {
        testKey = "testKey";
    }

    @Override
    public String getKey() {
        return testKey;
    }
}
