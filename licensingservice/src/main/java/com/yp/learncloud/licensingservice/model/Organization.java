package com.yp.learncloud.licensingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("organization")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Organization {
    @Id
    private String id;
    private String name;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
}
