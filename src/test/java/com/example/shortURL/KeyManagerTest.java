package com.example.shortURL;

import com.example.shortURL.service.KeyManager;
import com.example.shortURL.service.RandomKeyManager;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class KeyManagerTest {
    @Test
    public void URL_키_길이_테스트() {
        KeyManager testKeyManager = new RandomKeyManager();
        int keyLength = 8;

        testKeyManager.makeKey();
        assertThat(testKeyManager.getKey().length()).isEqualTo(keyLength);
    }
}
