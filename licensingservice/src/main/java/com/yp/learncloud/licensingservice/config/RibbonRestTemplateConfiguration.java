package com.yp.learncloud.licensingservice.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableOAuth2Sso
public class RibbonRestTemplateConfiguration {

    /**
     * ribbon客户端接口配置，通过{@link LoadBalanced}生效
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        //配置受保护资源的信息
        return new ClientCredentialsResourceDetails();
    }

    @LoadBalanced
    @Bean
    public OAuth2RestTemplate clientCredentialsRestTemplate(OAuth2ClientContext context, ClientCredentialsResourceDetails details) {
        return new OAuth2RestTemplate(details, context);
    }
}
