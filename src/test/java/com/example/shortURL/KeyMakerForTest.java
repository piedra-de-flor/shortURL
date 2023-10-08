package com.example.shortURL;

import com.example.shortURL.service.KeyMaker;

public class KeyMakerForTest implements KeyMaker {
    String testKey;
    @Override
    public String makeKey(int id) {
        double random = Math.random();
        testKey = "testKey" + random;
        return testKey;
    }

    public void makeKey(String input) {
        testKey = "testKey";
    }
}
