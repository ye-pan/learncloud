package com.yp.learncloud.licensingservice.clients;

import com.yp.learncloud.licensingservice.model.Organization;
import com.yp.learncloud.licensingservice.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrganizationOauth2Client {

    private OAuth2RestTemplate restTemplate;

    private OrganizationRepository organizationRepository;

    public OrganizationOauth2Client(OAuth2RestTemplate oAuth2RestTemplate, OrganizationRepository organizationRepository) {
        this.restTemplate = oAuth2RestTemplate;
        this.organizationRepository = organizationRepository;
    }

    private Organization getFromCache(String organizationId) {
        try {
            return organizationRepository.findById(organizationId).orElse(null);
        } catch(Exception e) {
            log.error("error.", e);
            return null;
        }
    }

    private void cacheOrganization(Organization organization) {
        organizationRepository.save(organization);
    }

    public Organization getOrganization(String organizationId) {
        Organization organization = getFromCache(organizationId);
        if(organization != null) {
            log.info("I have successfully retrieved an organization {} from the redis cache {}",
                    organizationId, organization);
            return organization;
        }
        String serviceUrl = "http://organizationservice/v1/organizations/{organizationId}";
        organization = restTemplate.getForObject(serviceUrl, Organization.class, organizationId);
        cacheOrganization(organization);
        return organization;
    }
}
