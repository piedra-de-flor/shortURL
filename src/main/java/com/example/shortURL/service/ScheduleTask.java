package com.example.shortURL.service;

import com.example.shortURL.domain.Url;
import com.example.shortURL.repository.Repository;
import com.example.shortURL.vo.LogVO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduleTask {
    private final UrlService crudManager;
    private final Logger log;

    @Autowired
    public ScheduleTask(UrlService crudManager, LogVO logVO) {
        this.crudManager = crudManager;
        this.log = logVO.getLog();
    }

    @Scheduled(cron = "59 59 23 * * ?",  zone = "Asia/Seoul")
    public void deleteExpirationDateUrl() {
        log.info("info log = {}", "Schedule task proceed");
        LocalDateTime now = LocalDateTime.now();
        List<Url> targets = crudManager.readAll()
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
