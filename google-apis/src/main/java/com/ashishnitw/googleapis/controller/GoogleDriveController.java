package com.ashishnitw.googleapis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoogleDriveController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
