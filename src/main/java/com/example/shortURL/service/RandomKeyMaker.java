package com.example.shortURL.service;

import org.springframework.stereotype.Service;

@Service
public class RandomKeyMaker implements KeyMaker {
    private final int KEY_LENGTH = 8;
    private final char[] BASE_62_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    @Override
    public String makeKey(int id) {
        StringBuilder sb = new StringBuilder();

        do {
            int value = id % 62;
            sb.append(BASE_62_CHAR[value]);
            id /= 62;
        } while (id > 0);

        if (sb.length() < KEY_LENGTH) {
            keyAppendInBlank(sb);
        }

        return sb.toString();
    }

    private void keyAppendInBlank(StringBuilder key) {
        int gap = KEY_LENGTH - key.length();

        for (int i = 0; i < gap; i++) {
            key.append("A");
        }
    }
}
