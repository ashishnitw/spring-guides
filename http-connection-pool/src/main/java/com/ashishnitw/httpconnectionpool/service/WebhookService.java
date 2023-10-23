package com.ashishnitw.httpconnectionpool.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WebhookService {

    private final RestTemplate restTemplate;

    private static final String WEBHOOK_URL = "https://webhook.site/5380cd63-d78b-4e88-9822-05497518ca57";

    public void webhook(Object request) throws IOException, InterruptedException {
        log.info("sending request #" + Thread.currentThread().getName());
        webhookPost(request);
        log.info("request sent #" + Thread.currentThread().getName());
    }

    public void webhookBulk(Object request) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    webhookPost(request);
                    log.info("request sent #" + Thread.currentThread().getName());
                } catch (Exception e) {
                    log.error("Error in webhookPost: {}", e.getMessage());
                }
            }).start();
        }
    }

    public void webhookPost(Object request) {
        long start = System.currentTimeMillis();
        restTemplate.postForEntity(WEBHOOK_URL, request, Object.class);
        long end = System.currentTimeMillis();
        log.info("time taken = " + (end - start) + " ms");
    }
}
