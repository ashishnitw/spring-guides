package com.ashishnitw.httpconnectionpool.config.apachehttpclient;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "http.pool.base")
public class HttpClientConfigs {
    private int maxTotalConnections;
    private int defaultMaxPerRouteConnections;
    private int connectTimeout;
    private int requestTimeout;
    private int socketTimeout;
    private int defaultKeepAliveTime;
    private int idleConnectionWaitTime;
}
