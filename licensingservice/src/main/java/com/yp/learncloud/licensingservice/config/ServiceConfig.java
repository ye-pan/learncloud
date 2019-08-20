package com.yp.learncloud.licensingservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ServiceConfig {
    @Value("${example.property}")
    private String exampleProperty;
}
