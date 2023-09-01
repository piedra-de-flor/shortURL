package com.example.shortURL;

import com.example.shortURL.controller.UrlController;
import com.example.shortURL.service.KeyMaker;
import com.example.shortURL.vo.LogVO;
import com.example.shortURL.service.UrlService;

public class UrlPropertyForTest {
    private final KeyMaker testKeyMaker = new KeyMakerForTest();
    private final UrlService testCrudManager = new UrlService(testKeyMaker, new LogVO());

    private final UrlController testController = new UrlController(testCrudManager);

    public UrlService getTestCrudManager() {
        return testCrudManager;
    }

    public UrlController getTestController() {
        return testController;
    }
}
