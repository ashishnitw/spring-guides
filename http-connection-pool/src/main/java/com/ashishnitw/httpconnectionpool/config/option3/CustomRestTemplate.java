package com.ashishnitw.httpconnectionpool.config.option3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class CustomRestTemplate {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
