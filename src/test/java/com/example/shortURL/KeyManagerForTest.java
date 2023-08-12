package com.example.shortURL;

import com.example.shortURL.service.KeyManager;

public class KeyManagerForTest implements KeyManager{
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
