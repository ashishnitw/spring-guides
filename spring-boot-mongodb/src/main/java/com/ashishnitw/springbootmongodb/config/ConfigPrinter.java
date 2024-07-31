package com.ashishnitw.springbootmongodb.config;

import com.mongodb.client.MongoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ConfigPrinter implements CommandLineRunner {
    private final MongoClient mongoClient;
    @Override
    public void run(String... args) {
        printDataSourceConfig();
    }
    private void printDataSourceConfig() {
        log.info("======================================");
        mongoClient.listDatabases().forEach(db -> log.info(db.toJson()));
        log.info("======================================");
    }

}
