package org.task.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.task.user.UserDao;
import org.task.user.UserService;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class AppConfig extends HikariConfig{

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/java")
                .username("postgres")
                .password("postgres")
                .build();
    }

    @Bean
    public UserDao getUserDao() {
        return new UserDao();
    }

    @Bean
    public UserService getUserService() {
        return new UserService(getUserDao());
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateResponseErrorHandler errorHandler) {
        return new RestTemplateBuilder().errorHandler(errorHandler).build();
    }
}
