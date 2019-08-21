package com.yp.learncloud.licensingservice.clients;

import com.yp.learncloud.licensingservice.model.Organization;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Component
public class OrganizationDisconveryClient {
    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate;

    public OrganizationDisconveryClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = new RestTemplate();
    }

    public Organization getOrganization(String organizationId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("ORGANIZATIONSERVICE");
        if(instances.isEmpty()) {
            return null;
        }

        String serviceUrl = String.format("%s/v1/organizations/%s", instances.get(0).getUri().toString(), organizationId);
        return restTemplate.getForObject(serviceUrl, Organization.class);
    }
}
