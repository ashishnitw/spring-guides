package com.ashishnitw.springbootmysql.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String hello() {
        return "Hello World";
    }
}
