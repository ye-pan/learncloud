package com.yp.learncloud.licensingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Organization {
    private String id;
    private String name;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
}
