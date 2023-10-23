package com.ashishnitw.springbootkafka.dto;

import lombok.Data;

@Data
public class EventRequest {
    private String topic;
    private String message;
}
