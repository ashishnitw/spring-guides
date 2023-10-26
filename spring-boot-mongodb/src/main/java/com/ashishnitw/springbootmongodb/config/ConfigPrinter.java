package com.ashishnitw.springbootmongodb.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ConfigPrinter implements CommandLineRunner {

    @Override
    public void run(String... args) {
        printDataSourceConfig();
    }

    private void printDataSourceConfig() {
        log.info("======================================");
        log.info("======================================");
    }

}
