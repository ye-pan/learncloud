package com.yp.learncloud.licensingservice.service;

import com.yp.learncloud.licensingservice.model.License;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LicenseService {
    public License getLicense(String id) {
        return new License(id, UUID.randomUUID().toString(), "Test Product Name", "PerSeat");
    }

    public void saveLicense(License license) {

    }

    public void updateLicense(License license) {

    }

    public void deleteLicense(License license) {

    }
}
