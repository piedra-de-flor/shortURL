package com.example.shortURL;

import com.example.shortURL.controller.UrlController;
import com.example.shortURL.service.KeyMaker;
import com.example.shortURL.service.UrlService;
import org.slf4j.Logger;

public class UrlPropertyForTest {
    private Logger log;
    private final KeyMaker testKeyMaker = new KeyMakerForTest();
    private final UrlService testCrudManager = new UrlService(testKeyMaker, log);

    private final UrlController testController = new UrlController(testCrudManager);

    public UrlService getTestCrudManager() {
        return testCrudManager;
    }

    public UrlController getTestController() {
        return testController;
    }
}
