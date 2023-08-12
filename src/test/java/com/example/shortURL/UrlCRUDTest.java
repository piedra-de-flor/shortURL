package com.example.shortURL;

import com.example.shortURL.controller.UrlCRUDController;
import com.example.shortURL.repository.Repository;
import com.example.shortURL.repository.UrlsCollection;
import com.example.shortURL.service.KeyManager;
import com.example.shortURL.service.UrlCRUDManager;

import java.util.ArrayList;

public class UrlCRUDTest {
    private final KeyManager testKeyManager = new KeyManagerForTest();
    private final UrlCRUDManager testCrudManager = new UrlCRUDManager(testKeyManager);

    private final UrlCRUDController testController = new UrlCRUDController(testCrudManager);

    public UrlCRUDManager getTestCrudManager() {
        return testCrudManager;
    }

    public UrlCRUDController getTestController() {
        return testController;
    }

    public void deleteAllTestData() {
    }
}
