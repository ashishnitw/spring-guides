package com.ashishnitw.restservice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Greeting {
    private final long id;
    private final String content;
}
