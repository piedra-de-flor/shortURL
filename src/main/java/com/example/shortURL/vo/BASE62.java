package com.example.shortURL.vo;

public enum BASE62 {
    BASE_62_LENGTH(62),
    BASE_62_COMPLETED_MOD(0),
    BASE_62_DECODING_POWER(1);

    public final int value;

    BASE62(int value) {
        this.value = value;
    }
}
