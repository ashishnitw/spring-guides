package com.ashishnitw.httpconnectionpool.config.option1;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@EnableScheduling
public class HttpClientConfig {

    @Value("${server.port}")
    private Integer port;

    private static final int DEFAULT_KEEP_ALIVE_TIME = 20 * 1000; // 20 seconds // Keep alive time
    private static final int IDLE_CONNECTION_WAIT_TIME = 20 * 1000; // 20 seconds // Idle connection monitor thread

    // Timeouts
    private static final int CONNECT_TIMEOUT = 30 * 1000; // 30 sec, the time for waiting until a connection is established
    private static final int REQUEST_TIMEOUT = 30 * 1000; // 30 sec, the time for waiting for a connection from connection pool
    private static final int SOCKET_TIMEOUT = 60 * 1000; // 60 sec, the time for waiting for data

    // Connection pool
    private static final int MAX_ROUTE_CONNECTIONS = 3; // max number of connections for a single route
    private static final int MAX_TOTAL_CONNECTIONS = 4; // max number of connections for all routes
    private static final int MAX_LOCALHOST_CONNECTIONS = 4; // max number of connections for localhost

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager();

        // set total amount of connections across all HTTP routes
        poolingConnectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);

        // set maximum amount of connections for each http route in pool
        poolingConnectionManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);

        // increase the amounts of connections if host is localhost
        HttpHost localhost = new HttpHost("http://localhost", port);
        poolingConnectionManager.setMaxPerRoute(new HttpRoute(localhost), MAX_LOCALHOST_CONNECTIONS);

        return poolingConnectionManager;
    }

    /**
     * A connection Keep-Alive strategy determines how long a connection may remain unused in the pool until it is closed.
     * This ensures that connections that are no longer needed are closed again promptly.
     **/
    @Bean
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return (httpResponse, httpContext) -> {
            HeaderIterator headerIterator = httpResponse.headerIterator(HTTP.CONN_KEEP_ALIVE);
            HeaderElementIterator elementIterator = new BasicHeaderElementIterator(headerIterator);

            while (elementIterator.hasNext()) {
                HeaderElement element = elementIterator.nextElement();
                String param = element.getName();
                String value = element.getValue();
                if (value != null && param.equalsIgnoreCase("timeout")) {
                    return Long.parseLong(value) * 1000; // convert to ms
                }
            }
            return DEFAULT_KEEP_ALIVE_TIME;
        };
    }

    /**
     * We want to configure a connection monitor that runs every 20 seconds and closes outdated connections as well as long waiting connections
     * **/
    @Bean
    public Runnable idleConnectionMonitor(PoolingHttpClientConnectionManager pool) {
        return new Runnable() {
            @Override
            @Scheduled(fixedDelay = 20000)
            public void run() {
                // only if connection pool is initialised
                if (pool != null) {
                    pool.closeExpiredConnections();
                    pool.closeIdleConnections(IDLE_CONNECTION_WAIT_TIME, TimeUnit.MILLISECONDS);
                    log.info("Idle connection monitor: Closing expired and idle connections");
                }
            }
        };
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("idleMonitor");
        scheduler.setPoolSize(5);
        return scheduler;
    }

    @Bean
    public CloseableHttpClient httpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT) // Maximum time that is waited for a connection to be established.
                .setConnectionRequestTimeout(REQUEST_TIMEOUT) // Maximum time that is waited until a connection from the connection pool is available.
                .setSocketTimeout(SOCKET_TIMEOUT) // Maximum time that is waited until data is received when a connection is established.
                .build();
        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(poolingHttpClientConnectionManager())
                .setKeepAliveStrategy(connectionKeepAliveStrategy())
                .build();
    }
}
