package com.example.shortURL.service;

import com.example.shortURL.domain.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduleTask {
    private final UrlCRUDManager crudManager;

    @Autowired
    public ScheduleTask(UrlCRUDManager crudManager) {
        this.crudManager = crudManager;
    }

    @Scheduled(cron = "59 59 23 * * ?",  zone="Asia/Seoul")
    public void deleteExpirationDateUrl() {
        System.out.println("schedule delete");
        LocalDateTime now = LocalDateTime.now();
        List<Url> targets = crudManager.readAll()
                .stream()
                .filter(url -> url.getDeleteDate().isBefore(now))
                .toList();

        for (Url url : targets) {
            crudManager.deleteUrl(url.getNewUrl());
        }
    }
}
