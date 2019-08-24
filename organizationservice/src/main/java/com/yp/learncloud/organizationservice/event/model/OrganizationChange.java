package com.yp.learncloud.organizationservice.event.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrganizationChange {
    private String type;
    private String action;
    private String organizationId;
    private String correlationId;
}
