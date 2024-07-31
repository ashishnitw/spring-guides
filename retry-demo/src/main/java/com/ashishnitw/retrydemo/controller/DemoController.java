package com.ashishnitw.retrydemo.controller;

import com.ashishnitw.retrydemo.service.DemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DemoController {

    private final DemoService demoService;

    @GetMapping("/test1")
    public String test1() {
        return demoService.processUsingSpringRetry();
    }

    @GetMapping("/test2")
    public String test2() {
        return demoService.processUsingSpringRetryCustom();
    }

}
