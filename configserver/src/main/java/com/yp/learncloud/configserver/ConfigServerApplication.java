package com.yp.learncloud.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "native");
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
