package com.ashishnitw.httpconnectionpool.config.option1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class CustomClientErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return response.getStatusCode().is4xxClientError();
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            log.info("CustomClientErrorHandler | HTTP Status Code: {}", response.getStatusCode().value());
        }
}
