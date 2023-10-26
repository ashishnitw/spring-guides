package com.ashishnitw.springbootmysql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class HelloController {

    @GetMapping("/hello")
    ResponseEntity<Object> hello() {
        return ResponseEntity.ok("Hello");
    }
}
