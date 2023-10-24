package com.ashishnitw.springbootmysql.config;

import com.zaxxer.hikari.HikariConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ConfigPrinter implements CommandLineRunner {

    private final HikariConfig hikariConfig;

    @Override
    public void run(String... args) {
        printHikariConfig();
    }

    private void printHikariConfig() {
        log.info("======================================");
        log.info("HikariCP Connection Pool Configuration : " + hikariConfig.getPoolName());
        log.info("\t JDBC URL: " + hikariConfig.getJdbcUrl());
        log.info("\t Username: " + hikariConfig.getUsername());
        log.info("\t Password: " + hikariConfig.getPassword());
        log.info("\t Connection Timeout: " + hikariConfig.getConnectionTimeout());
        log.info("\t Idle Timeout: " + hikariConfig.getIdleTimeout());
        log.info("\t Max Lifetime: " + hikariConfig.getMaxLifetime());
        log.info("\t Maximum Pool Size: " + hikariConfig.getMaximumPoolSize());
        log.info("\t Minimum Idle: " + hikariConfig.getMinimumIdle());
        log.info("======================================");
    }
}
