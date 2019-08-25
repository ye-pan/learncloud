package com.yp.learncloud.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
@EnableAuthorizationServer
@RefreshScope
public class AuthenticationApplication {
    public static void main(String[] args) {
        //TODO-yepan 还有个JWT 认证服务需要实现
        SpringApplication.run(AuthenticationApplication.class, args);
    }
}
