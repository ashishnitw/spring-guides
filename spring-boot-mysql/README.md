# DB Connection Pooling
1. HikariCP
2. Tomcat
3. Commons DBCP2

## HikariCP is the Default Connection Pool
If we’re using spring-boot-starter-data-jpa in the project, we don’t need to explicitly add the HikariCP dependency because it’s already included as a transitive dependency.

When we use this starter, Spring Boot will automatically configure a HikariCP connection pool based on sensible default settings. We can further customize the HikariCP configuration, if required, using the implementation-specific settings by changing their respective prefix (spring.datasource.hikari.*, spring.datasource.tomcat.*, and spring.datasource.dbcp2.*).

## Configuring Multiple DataSources with Spring Boot
To configure multiple data sources, create as many bean definitions as you want but mark one of the DataSource instances as @Primary.

Remember that if we create our own DataSource bean then auto-configuration backs off. In this case, we are responsible for providing configurations for all datasource beans.

