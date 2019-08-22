package com.yp.learncloud.zuulserver.configuration;

import com.yp.learncloud.zuulserver.utils.UserContextInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    /**
     * zuul会使用该RestTemplate调用服务
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new UserContextInterceptor());
        return restTemplate;
    }
}
