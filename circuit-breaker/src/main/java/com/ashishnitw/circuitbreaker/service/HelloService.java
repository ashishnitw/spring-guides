package com.ashishnitw.circuitbreaker.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HelloService {

    @HystrixCommand(
            fallbackMethod = "helloFallback",
            commandKey = "hello",
            threadPoolKey = "hello_tp"
    )
    public String hello() {
        return "hello";
    }

    public String helloFallback(Throwable e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return null;
    }
}
