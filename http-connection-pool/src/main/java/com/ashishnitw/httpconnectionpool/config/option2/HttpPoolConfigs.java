package com.ashishnitw.httpconnectionpool.config.option2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class HttpPoolConfigs {

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "http.pool.base")
    public static class BaseHttpPoolConfigs {
        private Integer maxTotalConnections;
        private Integer maxPerRouteConnections;
        private Integer connectTimeout;
        private Integer requestTimeout;
        private Integer socketTimeout;
    }


    @Configuration
    @ConfigurationProperties(prefix = "http.pool.webhook")
    public static class WebhookHttpPoolConfigs extends BaseHttpPoolConfigs { }
}
