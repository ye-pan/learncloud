package com.yp.learncloud.licensingservice.service;

import com.yp.learncloud.licensingservice.clients.ClientType;
import com.yp.learncloud.licensingservice.clients.OrganizationDisconveryClient;
import com.yp.learncloud.licensingservice.clients.OrganizationFeignClient;
import com.yp.learncloud.licensingservice.clients.OrganizationRibbonClient;
import com.yp.learncloud.licensingservice.config.ServiceConfig;
import com.yp.learncloud.licensingservice.model.License;
import com.yp.learncloud.licensingservice.model.Organization;
import com.yp.learncloud.licensingservice.repository.LicenseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class LicenseService {

    private LicenseRepository licenseRepository;

    private ServiceConfig serviceConfig;

    private OrganizationDisconveryClient organizationDisconveryClient;

    private OrganizationRibbonClient organizationRibbonClient;

    private OrganizationFeignClient organizationFeignClient;

    public LicenseService(LicenseRepository licenseRepository, ServiceConfig serviceConfig,
                          OrganizationDisconveryClient organizationDisconveryClient,
                          OrganizationRibbonClient organizationRibbonClient,
                          OrganizationFeignClient organizationFeignClient) {
        this.licenseRepository = licenseRepository;
        this.serviceConfig = serviceConfig;
        this.organizationDisconveryClient = organizationDisconveryClient;
        this.organizationRibbonClient = organizationRibbonClient;
        this.organizationFeignClient = organizationFeignClient;
    }

    private Organization retrieveOrgInfo(String organizationId, ClientType clientType) {
        switch(clientType) {
            case DISCONVERY_CLIENT:
                return organizationDisconveryClient.getOrganization(organizationId);
            case RIBBON:
                return organizationRibbonClient.getOrganization(organizationId);
            case FEIGN:
                return organizationFeignClient.getOrganization(organizationId);
            default:
                throw new UnsupportedOperationException();
        }
    }

    public License getLicense(String orgainzationId, String id) {
        License license = licenseRepository.getOne(id);
        license.setComment(serviceConfig.getExampleProperty());
        Organization organization = retrieveOrgInfo(orgainzationId, ClientType.FEIGN);
        license.setOrganization(organization);
        return license;
    }



    public void saveLicense(License license) {
        license.setId(UUID.randomUUID().toString());
        licenseRepository.save(license);
    }

    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    public void deleteLicense(License license) {
        licenseRepository.delete(license);
    }

    public List<License> getLicenseByOrg(String organizationId) {
        return licenseRepository.findByOrganizationId(organizationId);
    }

    public List<License> findAll() {
        return licenseRepository.findAll();
    }
}
