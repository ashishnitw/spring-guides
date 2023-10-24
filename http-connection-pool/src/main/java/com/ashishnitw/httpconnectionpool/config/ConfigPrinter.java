package com.ashishnitw.httpconnectionpool.config;

import com.ashishnitw.httpconnectionpool.config.apachehttpclient.HttpClientConfigs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ConfigPrinter implements CommandLineRunner {

    private final HttpClientConfigs httpConfig;
    @Override
    public void run(String... args) {
        printBaseHttpPooledConfig();
    }


    private void printBaseHttpPooledConfig() {
        log.info("======================================");
        log.info("Http Pooled Configs: " + httpConfig.getClass().getSimpleName());
        log.info("\t Max Total Connections: " + httpConfig.getMaxTotalConnections());
        log.info("\t Max Per Route Connections: " + httpConfig.getDefaultMaxPerRouteConnections());
        log.info("\t Connection Timeout: " + httpConfig.getConnectTimeout());
        log.info("\t Request Timeout: " + httpConfig.getRequestTimeout());
        log.info("\t Socket Timeout: " + httpConfig.getSocketTimeout());
        log.info("======================================");
    }

}
