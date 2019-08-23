package com.yp.learncloud.licensingservice.clients;

import com.yp.learncloud.licensingservice.model.Organization;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrganizationOauth2Client {

    private OAuth2RestTemplate restTemplate;

    public OrganizationOauth2Client(OAuth2RestTemplate oAuth2RestTemplate) {
        this.restTemplate = oAuth2RestTemplate;
    }

    public Organization getOrganization(String organizationId) {
        String serviceUrl = "http://organizationservice/v1/organizations/{organizationId}";
        return restTemplate.getForObject(serviceUrl, Organization.class, organizationId);
    }
}
