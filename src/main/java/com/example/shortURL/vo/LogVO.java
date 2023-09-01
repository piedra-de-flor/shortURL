package com.example.shortURL.vo;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogVO {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    public org.slf4j.Logger getLog() {
        return log;
    }
}