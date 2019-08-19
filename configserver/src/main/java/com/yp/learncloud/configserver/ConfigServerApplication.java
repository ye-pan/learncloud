package com.yp.learncloud.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        //TODO-yepan 调查为什么application.yml中的spring.profiles.active不生效
        System.setProperty("spring.profiles.active", "native");
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
