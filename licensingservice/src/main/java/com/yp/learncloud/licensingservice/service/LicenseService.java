package com.yp.learncloud.licensingservice.service;

import com.yp.learncloud.licensingservice.config.ServiceConfig;
import com.yp.learncloud.licensingservice.model.License;
import com.yp.learncloud.licensingservice.repository.LicenseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class LicenseService {

    private LicenseRepository licenseRepository;

    private ServiceConfig serviceConfig;

    public LicenseService(LicenseRepository licenseRepository, ServiceConfig serviceConfig) {
        this.licenseRepository = licenseRepository;
        this.serviceConfig = serviceConfig;
    }

    public License getLicense(String id) {
        License license = licenseRepository.findById(id).get();
        license.setComment(serviceConfig.getExampleProperty());
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
}
