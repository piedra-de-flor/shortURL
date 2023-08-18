package com.example.shortURL.vo;

public enum AsciiCodeIndexForRandomString {
    NUMBER_START_IN_ASCII_CODE(48),
    NUMBER_LIMIT_IN_ASCII_CODE(57),
    UPPER_ALPHABET_START_IN_ASCII_CODE(65),
    UPPER_ALPHABET_LIMIT_IN_ASCII_CODE(90),
    LOWER_ALPHABET_START_IN_ASCII_CODE(97),
    LOWER_ALPHABET_LIMIT_IN_ASCII_CODE(123);

    public final int index;

    AsciiCodeIndexForRandomString(int i) {
        this.index = i;
    }
}
