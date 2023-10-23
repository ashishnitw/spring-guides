package com.ashishnitw.springbootmysql.config;

import com.zaxxer.hikari.HikariConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@Component
public class HikariCPConfigPrinter implements CommandLineRunner {

    @Autowired
    private HikariConfig hikariConfig;

    @Override
    public void run(String... args) {
        printHikariConfig(hikariConfig);
    }

    private void printHikariConfig(HikariConfig config) {
        log.info("======================================");
        log.info("HikariCP Connection Pool Configuration : " + config.getPoolName());
        log.info("\t JDBC URL: " + config.getJdbcUrl());
        log.info("\t Username: " + config.getUsername());
        log.info("\t Password: " + config.getPassword());
        log.info("\t Maximum Pool Size: " + config.getMaximumPoolSize());
        log.info("\t Minimum Idle: " + config.getMinimumIdle());
        log.info("\t Connection Timeout: " + config.getConnectionTimeout());
        log.info("\t Idle Timeout: " + config.getIdleTimeout());
        log.info("\t Max Lifetime: " + config.getMaxLifetime());
        log.info("======================================");
    }
}
