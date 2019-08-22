package com.yp.learncloud.zuulserver.model;

import lombok.Data;

@Data
public class AbTestingRoute {
    private String serviceName;
    private String active;
    private String endpoint;
    private Integer weight;
}
