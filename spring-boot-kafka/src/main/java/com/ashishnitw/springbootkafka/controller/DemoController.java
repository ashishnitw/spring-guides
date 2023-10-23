package com.ashishnitw.springbootkafka.controller;

import com.ashishnitw.springbootkafka.dto.EventRequest;
import com.ashishnitw.springbootkafka.service.DemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DemoController {

    private final DemoService demoService;

    @PostMapping("/publish")
    public ResponseEntity<Object> publishMessage(@RequestBody EventRequest request) {
        try {
            demoService.publishMessage(request.getTopic(), request.getMessage());
            return ResponseEntity.ok().body("Message published successfully");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body("Error while publishing message");
        }
    }
}
