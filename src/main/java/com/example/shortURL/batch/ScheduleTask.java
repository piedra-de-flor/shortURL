package com.example.shortURL.batch;

import com.example.shortURL.domain.Url;
import com.example.shortURL.repository.Repository;
import com.example.shortURL.service.UrlService;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduleTask {
    private final UrlService crudManager;
    private final Logger log;

    public ScheduleTask(UrlService crudManager, Logger log) {
        this.crudManager = crudManager;
        this.log = log;
    }

    @Scheduled(cron = "59 59 23 * * ?",  zone = "Asia/Seoul")
    public void deleteExpirationDateUrl() {
        log.info("info log = {}", "Schedule task proceed");
        LocalDateTime now = LocalDateTime.now();
        List<Url> targets = crudManager.readAll().getUrls()
                .stream()
                .filter(url -> url.getDeleteDate().isBefore(now))
                .toList();

        for (Url url : targets) {
            crudManager.deleteUrl(url.getNewUrl());
        }
    }

    @Scheduled(cron = "59 59 23 L * ?", zone = "Asia/Seoul")
    public void infoDataBaseState() {
        Repository DB = crudManager.getDB();
        int currentSize = DB.getDataSize();
        log.info("info log = there are {} datas in DB", currentSize);
    }
}
