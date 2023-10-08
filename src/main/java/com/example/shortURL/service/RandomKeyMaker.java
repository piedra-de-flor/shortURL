package com.example.shortURL.service;

import com.example.shortURL.vo.BASE62;
import org.springframework.stereotype.Service;

@Service
public class RandomKeyMaker implements KeyMaker {
    private final int KEY_LENGTH = 8;
    private final int BASE_62_LENGTH = 62;
    private final char[] BASE_62_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    @Override
    public String makeKey(int id) {
        StringBuilder sb = new StringBuilder();

        do {
            int value = id % BASE_62_LENGTH;
            sb.append(BASE_62_CHAR[value]);
            id /= BASE_62_LENGTH;
        } while (id > 0);

        if (sb.length() < KEY_LENGTH) {
            keyAppendInBlank(sb);
        }

        return sb.toString();
    }

    @Override
    public int decodeKey(String newUrl) {
        int result = BASE62.BASE_62_COMPLETED_MOD.value;
        int power = BASE62.BASE_62_DECODING_POWER.value;

        for (int i = 0; i < newUrl.length(); i++) {
            int digit = new String(BASE_62_CHAR).indexOf(newUrl.charAt(i));
            result += digit * power;
            power *= BASE62.BASE_62_LENGTH.value;
        }

        return result;
    }

    private void keyAppendInBlank(StringBuilder key) {
        int gap = KEY_LENGTH - key.length();

        for (int i = 0; i < gap; i++) {
            key.append("A");
        }
    }
}
