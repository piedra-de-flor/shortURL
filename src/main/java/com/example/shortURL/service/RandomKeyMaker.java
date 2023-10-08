package com.example.shortURL.service;

import com.example.shortURL.vo.AsciiCodeIndexForRandomString;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomKeyMaker implements KeyMaker {
    private final int KEY_LENGTH = 8;
    private String randomKey;
    private final Random random = new Random();

    @Override
    public void makeKey() {
        randomKey = levelingKey(random.ints(AsciiCodeIndexForRandomString.NUMBER_START_IN_ASCII_CODE.index, AsciiCodeIndexForRandomString.LOWER_ALPHABET_LIMIT_IN_ASCII_CODE.index)
                .filter(i -> (i <= AsciiCodeIndexForRandomString.NUMBER_LIMIT_IN_ASCII_CODE.index || i >= AsciiCodeIndexForRandomString.UPPER_ALPHABET_START_IN_ASCII_CODE.index)
                        && (i <= AsciiCodeIndexForRandomString.UPPER_ALPHABET_LIMIT_IN_ASCII_CODE.index || i >= AsciiCodeIndexForRandomString.LOWER_ALPHABET_START_IN_ASCII_CODE.index))
                .limit(KEY_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString());
    }

    private String levelingKey(String randomKey) {
        return randomKey.replaceAll("/[il]/g", "1");
    }

    public String getKey() {
        return randomKey;
    }
}
