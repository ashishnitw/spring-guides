package com.ashishnitw.httpconnectionpool.config.option1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("Error in sleep: {}", e.getMessage());
        }
        log.info("URI: {}, HTTP Method: {}, HTTP Headers: {}", request.getURI(), request.getMethod(), request.getHeaders());
        return execution.execute(request, body);
    }
}
