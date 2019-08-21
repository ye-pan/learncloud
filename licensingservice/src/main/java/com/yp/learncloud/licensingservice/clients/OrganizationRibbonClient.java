package com.yp.learncloud.licensingservice.clients;

import com.yp.learncloud.licensingservice.model.Organization;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrganizationRibbonClient {
    private RestTemplate restTemplate;

    public OrganizationRibbonClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Organization getOrganization(String organizationId) {
        String serviceUrl = "http://organizationservice/v1/organizations/{organizationId}";
        return restTemplate.getForObject(serviceUrl, Organization.class, organizationId);
    }
}
