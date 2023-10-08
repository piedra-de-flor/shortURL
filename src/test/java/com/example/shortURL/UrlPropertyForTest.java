package com.example.shortURL;

import com.example.shortURL.configuration.LogConfig;
import com.example.shortURL.controller.UrlController;
import com.example.shortURL.dto.UrlMakeRequestDto;
import com.example.shortURL.service.KeyMaker;
import com.example.shortURL.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlPropertyForTest {
   final Logger log = LoggerFactory.getLogger(LogConfig.class);;
    private final KeyMaker testKeyMaker = new KeyMakerForTest();
    private final UrlService testCrudManager = new UrlService(testKeyMaker, log);

    private final UrlController testController = new UrlController(testCrudManager);

    public UrlService getTestCrudManager() {
        return testCrudManager;
    }

    public UrlController getTestController() {
        return testController;
    }

    public void makeTestUrl(String input) {
        UrlMakeRequestDto testUrl = new UrlMakeRequestDto();
        testUrl.setOriginUrl(input);

        this.testController.makeUrl(testUrl);
    }
}
