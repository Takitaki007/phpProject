package com.example.springhomework002.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setUrl("jdbc:postgresql:///mybatisdb");
        source.setUsername("postgres");
        source.setPassword("123");
        return source;
    }
}
