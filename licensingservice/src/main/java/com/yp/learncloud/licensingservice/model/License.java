package com.yp.learncloud.licensingservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class License {
    private String id;
    private String organizationId;
    private String projectName;
    private String licenseType;
}
