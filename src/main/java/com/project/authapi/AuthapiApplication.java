package com.project.authapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.project.authapi")
public class AuthapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthapiApplication.class, args);
    }
}
