package com.example.shortURL.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomKeyManager implements KeyManager {
    private final int NUMBER_START_IN_ASCII_CODE = 48;
    private final int NUMBER_LIMIT_IN_ASCII_CODE = 57;
    private final int UPPER_ALPHABET_START_IN_ASCII_CODE = 65;
    private final int UPPER_ALPHABET_LIMIT_IN_ASCII_CODE = 90;
    private final int LOWER_ALPHABET_START_IN_ASCII_CODE = 97;
    private final int LOWER_ALPHABET_LIMIT_IN_ASCII_CODE = 123;
    private final int KEY_LENGTH = 8;
    private String randomKey;
    private final Random random = new Random();

    @Override
    public void makeKey() {
        randomKey = random.ints(NUMBER_START_IN_ASCII_CODE, LOWER_ALPHABET_LIMIT_IN_ASCII_CODE)
                .filter(i -> (i <= NUMBER_LIMIT_IN_ASCII_CODE || i >= UPPER_ALPHABET_START_IN_ASCII_CODE)
                        && (i <= UPPER_ALPHABET_LIMIT_IN_ASCII_CODE || i >= LOWER_ALPHABET_START_IN_ASCII_CODE))
                .limit(KEY_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public String getKey() {
        return randomKey;
    }
}
