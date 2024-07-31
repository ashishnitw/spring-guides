package com.ashishnitw.retrydemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class DemoService {

    private boolean flag = true;

    @Retryable
    public String processUsingSpringRetry() {
        log.info("Inside processUsingSpringRetry method - " + new Date());
        if (flag)
            throw new RuntimeException("processUsingSpringRetry");
        return "Success";
    }

    @Recover
    public String processUsingSpringRetryRecover(RuntimeException e) {
        log.info("Inside recover of processUsingSpringRetry method - " + new Date());
        return "Failed";
    }

    @Retryable(
            retryFor = RuntimeException.class,
            maxAttempts = 5,
            backoff = @Backoff(
                    delay = 100,
                    maxDelay = 10000,
                    multiplier = 2
            ))
    public String processUsingSpringRetryCustom() {
        log.info("Inside method..." + new Date());
        if (flag)
            throw new RuntimeException("processUsingSpringRetryCustom");
        return "Success";
    }

    @Recover
    public String processUsingSpringRetryCustomRecover(RuntimeException e) {
        log.info("Inside recover of processUsingSpringRetryCustom method - " + new Date());
        return "Failed";
    }
}
